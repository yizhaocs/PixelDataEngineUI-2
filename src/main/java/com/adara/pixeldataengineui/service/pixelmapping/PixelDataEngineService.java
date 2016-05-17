package com.adara.pixeldataengineui.service.pixelmapping;

import com.opinmind.pixeldataengine.PixelDataEngine;
import org.springframework.stereotype.Service;

@Service("pixelDataEngineService")
public class PixelDataEngineService {
    PixelDataEngine mPixelDataEngine;

    public PixelDataEngine getmPixelDataEngine() {
        return mPixelDataEngine;
    }

    public void setmPixelDataEngine(PixelDataEngine mPixelDataEngine) {
        this.mPixelDataEngine = mPixelDataEngine;
    }
}