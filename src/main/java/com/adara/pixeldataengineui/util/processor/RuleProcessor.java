package com.adara.pixeldataengineui.util.processor;


import com.adara.pixeldataengineui.util.model.PixelDataEngineConfig;

import java.util.Map;

/**
 * @author jgao
 *
 */
public interface RuleProcessor {
    /**
     * this method gets the input which is typically a key->value pair ( in future we can support multiple key->value pairs )
     * process the data and return the processed key->value pairs.
     *
     * for example, the origKeyValues could be {10636 : 'ECONOMY:ALA:AUH-AUH:ALA'}
     * and the output consists of 3 key->value pairs : {10636: 'ECONOMY:ALA:AUH-AUH:ALA', 10936: 'ALA', 10937: 'AUH' }
     *
     * Note, we need to return the original key and its value all the time. If the original key is not in the map,
     * it WON'T get logged.
     *
     * @param key
     * @param value
     * @return the target key->value pairs
     * @throws Exception
     */
    public Map<String, String> processRule(String key, String value, PixelDataEngineConfig config) throws Exception;

}
