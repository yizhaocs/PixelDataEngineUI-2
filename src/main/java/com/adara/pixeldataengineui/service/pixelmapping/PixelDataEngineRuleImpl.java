package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.PixelDataEngineRuleDAO;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("pixelDataEngineRuleService")
@Transactional
public class PixelDataEngineRuleImpl implements PixelDataEngineRuleService {
    private static final Log LOG = LogFactory.getLog(PixelDataEngineRuleImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    private PixelDataEngineRuleDAO mPixelDataEngineRuleDAO;

    public Integer insertRule(RuleRequest request, Boolean isUITest) throws Exception {
        return mPixelDataEngineRuleDAO.insertRule(request, isUITest);
    }

    public String getRules() throws Exception {
        return mPixelDataEngineRuleDAO.getRules();
    }

    public String getRule(Integer gid, String keyId, Integer priority) throws Exception {
        return mPixelDataEngineRuleDAO.getRule(gid, keyId, priority);
    }

    public Integer updateRule(RuleRequest request) throws Exception {
        return mPixelDataEngineRuleDAO.updateRule(request);
    }

    public Integer deleteRule(Integer gid, String keyId, Integer priority, Boolean isUITest) throws Exception {
        return mPixelDataEngineRuleDAO.deleteRule(gid, keyId, priority, isUITest);
    }

    public Map<String, String> testRule(PixelDataEngineService mPixelDataEngineService, PixelDataEngineRuleService mPixelDataEngineRuleService, PixelDataEngineGroupService mPixelDataEngineGroupService, RuleRequest request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "testRule" + "]";

        Map<String, String> treeMapResultMap = new TreeMap<String, String>();

        if (request.getTestOption().equals("individual")) {
            String testKeyID = request.getKeyId();
            String testValue = request.getTestValue();

            // insert group
            mPixelDataEngineGroupService.insertGroup(testKeyID, 1, true);
            // insert rule
            request.setGid("1");
            request.setPriority(request.getNewPriority());
            mPixelDataEngineRuleService.insertRule(request, true);

            // let the mock table refresh its pixelDataEngineConfigs
            mPixelDataEngineService.mPixelDataEngine.init();
            Map<String, String> resultMap = mPixelDataEngineService.mPixelDataEngine.processRule(testKeyID, testValue);

            // delete group
            mPixelDataEngineGroupService.deleteGroup(testKeyID, "1", true);
            // delete rule
            mPixelDataEngineRuleService.deleteRule(1, testKeyID, Integer.valueOf(request.getNewPriority()), true);


            // reverse order
            treeMapResultMap.putAll(resultMap);
        } else if (request.getTestOption().equals("group")) {
            String testGroupID = request.getGid();
            String testKeyID = request.getKeyId();
            String testValue = request.getTestValue();

            // group = {"trigger_key_id":"3003","gid":3003,"group_type":2}
            String group = mPixelDataEngineGroupService.getGroup(testKeyID);
            String removeFirstLastCurlyBracesGroup = group.substring(1, group.length() - 1);
            String[] groupSplitLevel1 = removeFirstLastCurlyBracesGroup.split(",");

            String triggerKeyIdNew = null;
            String gidNew = null;
            String groupTypeNew = null;

            for (int i = 0; i < groupSplitLevel1.length; i++) {
                String[] groupSplitLevel2 = groupSplitLevel1[i].split(":");
                if (i == 0) {
                    triggerKeyIdNew = groupSplitLevel2[1].substring(1, groupSplitLevel2[1].length() - 1);
                } else if (i == 2) {
                    groupTypeNew = groupSplitLevel2[1];
                }
            }

            mPixelDataEngineGroupService.insertGroup(triggerKeyIdNew, Integer.valueOf(groupTypeNew), true);

            // [{"gid":"3003","key_id":"3003","priority":"1","type":"transform","parse_rule":"orig","condition_rule":"len|1|4","action_rule":"substr|L|0|3"},{"gid":"3003","key_id":"3003","priority":"2","type":"transform","parse_rule":"orig","condition_rule":"len|1|4","action_rule":"substr|R|0|2"},{"gid":"3003","key_id":"3003","priority":"3","type":"transform","parse_rule":"orig","condition_rule":"len|1|4","action_rule":"substr|L|0|1"}]
            GenericDTOList<PixelDataEngineConfigsDTO> sameGroupRules = mPixelDataEngineGroupService.getSameGroup(Integer.valueOf(testGroupID));
            Collection<PixelDataEngineConfigsDTO> sameGroupRulesList = sameGroupRules.getList();
            for (PixelDataEngineConfigsDTO mPixelDataEngineConfigsDTO : sameGroupRulesList) {
                RuleRequest mRuleRequest = new RuleRequest(mPixelDataEngineConfigsDTO.getParse_rule(), mPixelDataEngineConfigsDTO.getCondition_rule(), null, mPixelDataEngineConfigsDTO.getAction_rule(), "1", mPixelDataEngineConfigsDTO.getKey_id(), mPixelDataEngineConfigsDTO.getPriority(), null, mPixelDataEngineConfigsDTO.getType(), null, null, null, null, null, null, null, null, null, null, null);
                mPixelDataEngineRuleService.insertRule(mRuleRequest, true);
            }
            // let the mock table refresh its pixelDataEngineConfigs
            mPixelDataEngineService.mPixelDataEngine.init();
            Map<String, String> resultMap = mPixelDataEngineService.mPixelDataEngine.processRule(testKeyID, testValue);

            // delete group
            mPixelDataEngineGroupService.deleteGroup(testKeyID, "1", true);
            // delete rule
            mPixelDataEngineRuleService.deleteRule(1, testKeyID, Integer.valueOf(request.getNewPriority()), true);
            // reverse order
            treeMapResultMap.putAll(resultMap);
        }

        return treeMapResultMap;
    }
}
