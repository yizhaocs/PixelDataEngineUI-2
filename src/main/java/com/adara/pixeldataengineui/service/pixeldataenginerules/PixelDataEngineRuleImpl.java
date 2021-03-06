package com.adara.pixeldataengineui.service.pixeldataenginerules;

import com.adara.pixeldataengineui.dao.pixeldataenginerules.PixelDataEngineRuleDAO;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineGroupsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.TestRuleDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;
import com.opinmind.pixeldataengine.cache.MapCache;

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
    private PixelDataEngineService mPixelDataEngineService;

    @Autowired
    private PixelDataEngineRuleDAO mPixelDataEngineRuleDAO;

    public ResponseDTO insertRule(RuleRequest request, Boolean isUITest) throws Exception {
        return mPixelDataEngineRuleDAO.insertRule(request, isUITest);
    }

    public GenericDTOList<PixelDataEngineConfigsDTO> getRules() throws Exception {
        return mPixelDataEngineRuleDAO.getRules();
    }

    public PixelDataEngineConfigsDTO getRule(Integer gid, String keyId, Integer priority) throws Exception {
        return mPixelDataEngineRuleDAO.getRule(gid, keyId, priority);
    }

    public ResponseDTO updateRule(RuleRequest request) throws Exception {
        return mPixelDataEngineRuleDAO.updateRule(request);
    }

    public ResponseDTO deleteRule(Integer gid, String keyId, Integer priority, Boolean isUITest) throws Exception {
        return mPixelDataEngineRuleDAO.deleteRule(gid, keyId, priority, isUITest);
    }

    public void truncatePixelDataEngineConfigsTable(Boolean isUITest) throws Exception {
        mPixelDataEngineRuleDAO.truncatePixelDataEngineConfigsTable(isUITest);
    }

    public GenericDTOList<TestRuleDTO> testRule(PixelDataEngineRuleService mPixelDataEngineRuleService, PixelDataEngineGroupService mPixelDataEngineGroupService, RuleRequest request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "testRule" + "]";

        GenericDTOList<TestRuleDTO> result = new GenericDTOList<TestRuleDTO>();
        MapCache mapCache = initMapCache();
        /*
        * truncate pixel_data_engine_groups
        * */
        mPixelDataEngineGroupService.truncatePixelDataEngineGroupsTable(true);

        /*
        * truncate pixel_data_engine_configs
        * */
        mPixelDataEngineRuleService.truncatePixelDataEngineConfigsTable(true);


        Map<String, String> treeMapResultMap = new TreeMap<String, String>();

        if (request.getTestOption().equals("individual")) {
            String testKeyID = request.getKeyId();
            String testValue = request.getTestValue();

            /*
            * insert group
            * */
            mPixelDataEngineGroupService.insertGroup(testKeyID, 1, true);

            /*
            * insert the latest changed rule on the webpage
            * */
            request.setGid("1");
            request.setPriority(request.getNewPriority());
            mPixelDataEngineRuleService.insertRule(request, true);

            /*
            * init mapCache
            * */
            mPixelDataEngineService.mPixelDataEngine.setMapCache(mapCache);

            /*
            * let the mock table refresh its pixelDataEngineConfigs
            * */
            mPixelDataEngineService.mPixelDataEngine.init();
            Map<String, String> resultMap = mPixelDataEngineService.mPixelDataEngine.processRule(testKeyID, testValue);

            /*
            * reverse order
            * */
            treeMapResultMap.putAll(resultMap);
        } else if (request.getTestOption().equals("group")) {
            String testGroupID = request.getGid();
            String testKeyID = request.getKeyId();
            String testValue = request.getTestValue();

            // group = {"trigger_key_id":"3003","gid":3003,"group_type":2}
            PixelDataEngineGroupsDTO group = mPixelDataEngineGroupService.getGroup(testGroupID);

            String triggerKeyIdNew = group.getTrigger_key_id();
            String gidNew = null;
            Integer groupTypeNew = group.getGroup_type();

            mPixelDataEngineGroupService.insertGroup(triggerKeyIdNew, groupTypeNew, true);

            // List [list=[PixelDataEngineConfigsDTO [gid=1001, key_id=1001, priority=1, type=transform, parse_rule=orig, condition_rule=len|1|4, action_rule=substr|L|0|1], PixelDataEngineConfigsDTO [gid=1001, key_id=1001, priority=2, type=transform, parse_rule=orig, condition_rule=in|a, action_rule=ignore]]]
            GenericDTOList<PixelDataEngineConfigsDTO> sameGroupRules = mPixelDataEngineGroupService.getSameGroup(Integer.valueOf(testGroupID));
            Collection<PixelDataEngineConfigsDTO> sameGroupRulesList = sameGroupRules.getList();
            for (PixelDataEngineConfigsDTO mPixelDataEngineConfigsDTO : sameGroupRulesList) {
                if (mPixelDataEngineConfigsDTO.getPriority().equals(request.getPriority()) == false) {
                    RuleRequest mRuleRequest = new RuleRequest(mPixelDataEngineConfigsDTO.getParse_rule(), mPixelDataEngineConfigsDTO.getCondition_rule(), mPixelDataEngineConfigsDTO.getAction_rule(), "1", mPixelDataEngineConfigsDTO.getKey_id(), mPixelDataEngineConfigsDTO.getPriority(), null, mPixelDataEngineConfigsDTO.getType(), null, null, null, null, null, null, null, null, null, null, null, null, null);
                    mPixelDataEngineRuleService.insertRule(mRuleRequest, true);
                }
            }

            /*
            * insert the latest changed rule on the webpage
            * */
            request.setGid("1");
            request.setPriority(request.getNewPriority());
            mPixelDataEngineRuleService.insertRule(request, true);

            /*
            * init mapCache
            * */
            mPixelDataEngineService.mPixelDataEngine.setMapCache(mapCache);

            /*
            * let the mock table refresh its pixelDataEngineConfigs
            * */
            mPixelDataEngineService.mPixelDataEngine.init();
            Map<String, String> resultMap = mPixelDataEngineService.mPixelDataEngine.processRule(triggerKeyIdNew, testValue);

            /*
            * reverse order
            * */
            treeMapResultMap.putAll(resultMap);
        }

        /*
        * truncate pixel_data_engine_groups
        * */
        mPixelDataEngineGroupService.truncatePixelDataEngineGroupsTable(true);

        /*
        * truncate pixel_data_engine_configs
        * */
        mPixelDataEngineRuleService.truncatePixelDataEngineConfigsTable(true);


        for (String key : treeMapResultMap.keySet()) {
            TestRuleDTO mTestRuleDTO = new TestRuleDTO(key, treeMapResultMap.get(key));
            result.add(mTestRuleDTO);
        }

        return result;
    }


    private MapCache initMapCache() {
        MapCache mapCache = new MapCache() {
            @Override
            public String getMapping(String mapName, String value) {
                if (mapName.equals("dummy")) {
                    if (value.equals("NY")) {
                        return "NEW YORK";
                    }
                    return value;
                }
                return "Please use 'city' table for testing map action purpose";
            }

            @Override
            public void setMapping(String mapName, String value,
                                   String mappedValue) {
                // do nothing
            }

            public String printInfo() {
                // TODO Auto-generated method stub
                return null;
            }
        };

        return mapCache;
    }
}
