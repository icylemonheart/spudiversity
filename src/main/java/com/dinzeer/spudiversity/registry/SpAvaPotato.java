package com.dinzeer.spudiversity.registry;

import com.dinzeer.legendreliclib.lib.tags.CuriosTag;

import com.dinzeer.spudiversity.item.speaical.NeutroniumPotato;
import com.dinzeer.spudiversity.item.speaical.UniversePotato;
import net.minecraft.world.item.Item;
import com.tterrag.registrate.util.entry.RegistryEntry;

import static com.dinzeer.spudiversity.Spudiversity.REGISTRATE;

public class SpAvaPotato {
    public static final RegistryEntry<Item, UniversePotato> UniversePotato = REGISTRATE.item(
                  "universe_potato", UniversePotato::new
            )
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), "item/generated")
                    .texture("layer0", "item/avaritia/universe_potato")
            ).defaultLang().register();
    public static final RegistryEntry<Item, NeutroniumPotato> NeutroniumPotato = REGISTRATE.item(
                    "neutronium_potato", NeutroniumPotato::new
            )
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), "item/generated")
                    .texture("layer0", "item/avaritia/neutronium_potato")
            )
            .tag(CuriosTag.POTATO)
            .defaultLang().register();
    public static void register() {
        SpPotato.ToolTipBlackList.add(UniversePotato);
        SpPotato.ToolTipBlackList.add(NeutroniumPotato);
    }
}
