package com.dinzeer.spudiversity.registry;

import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
import com.dinzeer.legendreliclib.lib.util.impl.RegisteredStackManager;

import static com.dinzeer.spudiversity.Spudiversity.MODID;

public class SpStackReg {
   public static IStackManager BloodKingCD=register("blood_king_cd", 10);
    
    public static IStackManager register(String name, int max){
        return RegisteredStackManager.Registry.register(MODID+":"+name, max);
    }
}