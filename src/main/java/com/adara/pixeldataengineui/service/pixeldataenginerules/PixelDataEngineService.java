package com.adara.pixeldataengineui.service.pixeldataenginerules;

import com.opinmind.pixeldataengine.PixelDataEngine;
import org.springframework.stereotype.Service;

@Service("pixelDataEngineService")
public class PixelDataEngineService {
    PixelDataEngine mPixelDataEngine;

    public PixelDataEngine getmPixelDataEngine() throws Exception {
        return mPixelDataEngine;
    }

    public void setmPixelDataEngine(PixelDataEngine mPixelDataEngine) throws Exception {
        this.mPixelDataEngine = mPixelDataEngine;
    }
}
