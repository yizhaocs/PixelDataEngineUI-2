package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.DbmConversionPixelMappingsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("dbmConversionPixelMappingsService")
@Transactional
public class DbmConversionPixelMappingsServiceImpl implements DbmConversionPixelMappingsService{
    @Autowired
    private DbmConversionPixelMappingsDAO mDbmConversionPixelMappingsDAO;

    public String getMappings(){
        return mDbmConversionPixelMappingsDAO.getMappings();
    }

    public String getMapping(String conversionPixelId){
        return mDbmConversionPixelMappingsDAO.getMapping(conversionPixelId);
    }

    public Integer insertMapping(Integer conversionPixelId, String dbmUrl){
        return mDbmConversionPixelMappingsDAO.insertMapping(conversionPixelId, dbmUrl);
    }

    public Integer updateMapping(Integer conversionPixelId, String dbmUrl){
        return mDbmConversionPixelMappingsDAO.updateMapping(conversionPixelId, dbmUrl);
    }

    public Integer deleteMapping(String conversionPixelId){
        return mDbmConversionPixelMappingsDAO.deleteMapping(conversionPixelId);
    }
}
