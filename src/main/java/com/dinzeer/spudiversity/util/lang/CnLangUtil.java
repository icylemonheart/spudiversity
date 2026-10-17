package com.dinzeer.spudiversity.util.lang;

import com.dinzeer.spudiversity.registry.*;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

import static com.dinzeer.spudiversity.Spudiversity.REGISTRATE;

public class CnLangUtil {
    public static void init(){
        REGISTRATE.addRawLang("item_group.spudiversity.spudiversity", "土豆盛宴");

        // 永续土豆 tooltip 共享布局文案
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.tag", "【永续土豆】");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.effects", "【效果】");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.obtain", "【获取】");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.shift_hint", "按下「%s」查看具体效果与获取方式");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.shift_hint_values", "按下「%s」查看具体数值");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.active", "当前生效的永续土豆：%s");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.none", "当前没有永续土豆生效");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.off_active", "独立槽位生效中：%s");
        REGISTRATE.addRawLang("tooltip.spudiversity.perpetual.off_none", "独立槽位未生效");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.effects", "【佩戴效果】");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.obtain", "【获取】");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.zzz_killed", "已击杀首领：%s");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.zzz_none", "尚未击杀任何首领");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.edelweiss_attack_damage", "攻击伤害加成：+%s");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.edelweiss_attack_speed", "攻击速度加成：+%s%%");
        REGISTRATE.addRawLang("tooltip.spudiversity.boss.wither", "凋零");
        REGISTRATE.addRawLang("tooltip.spudiversity.boss.elder_guardian", "远古守卫者");
        REGISTRATE.addRawLang("tooltip.spudiversity.boss.ender_dragon", "末影龙");
        REGISTRATE.addRawLang("tooltip.spudiversity.boss.warden", "监守者");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.memory_now", "当前内存占用率：%s%%，伤害 ×%s");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.jijin_now", "当前正面效果：%s 种 → 攻击 +%s%%、护甲韧性 +%s、最大生命 +%s%%");
        REGISTRATE.addRawLang("tooltip.spudiversity.charm.skyfox_now", "当前幸运值：%s → 受伤 -%s 点、攻击 +%s 点");

        REGISTRATE.addRawLang("item.spudiversity.coal_potatoes.tooltip", "\"似乎是凋零骷髅的主食\"");
        REGISTRATE.addRawLang("item.spudiversity.coal_potatoes.tooltip_1", "吃下后获得「熔炉祝福」效果");
        REGISTRATE.addRawLang("item.spudiversity.coal_potatoes.tooltip_2", "效果持续期间挖掘会自动冶炼");

        REGISTRATE.addRawLang("item.spudiversity.copper_potato.tooltip", "\"口感...还行？\"");
        REGISTRATE.addRawLang("item.spudiversity.copper_potato.tooltip_1", "吃下后获得「避雷针体质」效果");
        REGISTRATE.addRawLang("item.spudiversity.copper_potato.tooltip_2", "效果持续期间会吸引闪电但是也会得到80%雷电伤害类型减免");

        REGISTRATE.addRawLang("item.spudiversity.carrots_potato.tooltip", "\"像是在吃土豆炖胡萝卜，可这不是烤的吗\"");
        REGISTRATE.addRawLang("item.spudiversity.carrots_potato.tooltip_1", "吃下后获得「夜视」效果");
        REGISTRATE.addRawLang("item.spudiversity.carrots_potato.tooltip_2", "吃下后获得「跳跃提升」效果");

        REGISTRATE.addRawLang("item.spudiversity.laps_potato.tooltip", "\"九九成，稀罕物\"");
        REGISTRATE.addRawLang("item.spudiversity.laps_potato.tooltip_1", "吃下后获得「水下呼吸」效果");
        REGISTRATE.addRawLang("item.spudiversity.laps_potato.tooltip_2", "并不能拿来当附魔媒介");

        REGISTRATE.addRawLang("item.spudiversity.iron_potato.tooltip", "\"让暴风雨来的更猛烈些吧！\"");
        REGISTRATE.addRawLang("item.spudiversity.iron_potato.tooltip_1", "吃下后获得「抗性提升2」效果");
        REGISTRATE.addRawLang("item.spudiversity.iron_potato.tooltip_2", "持续20s");

        REGISTRATE.addRawLang("item.spudiversity.redstone_potato.tooltip", "\"据说第一个这玩意的诞生来自不想掉落红石的女巫\"");
        REGISTRATE.addRawLang("item.spudiversity.redstone_potato.tooltip_1", "吃下后获得「速度2」效果");
        REGISTRATE.addRawLang("item.spudiversity.redstone_potato.tooltip_2", "持续1分钟");

        REGISTRATE.addRawLang("item.spudiversity.gold_potato.tooltip", "\"嗯，九九成，稀罕物儿\"");
        REGISTRATE.addRawLang("item.spudiversity.gold_potato.tooltip_1", "吃下后获得「生命恢复」效果");
        REGISTRATE.addRawLang("item.spudiversity.gold_potato.tooltip_2", "吃下后获得「抗型提升」效果");

        REGISTRATE.addRawLang("item.spudiversity.diamond_potato.tooltip", "\"据说比沙漠之星更昂贵的，是用沙漠之星做的土豆\"");
        REGISTRATE.addRawLang("item.spudiversity.diamond_potato.tooltip_1", "吃下后获得「抗性提升3」效果");
        REGISTRATE.addRawLang("item.spudiversity.diamond_potato.tooltip_2", "持续30s");

        REGISTRATE.addRawLang("item.spudiversity.obsidian_potato.tooltip", "\"这这能吃吗\"");
        REGISTRATE.addRawLang("item.spudiversity.obsidian_potato.tooltip_1", "吃下后获得「抗性提升2」效果");
        REGISTRATE.addRawLang("item.spudiversity.obsidian_potato.tooltip_2", "吃下后获得「防火」效果");

        REGISTRATE.addRawLang("item.spudiversity.emerald_potato.tooltip", "\"拥抱新生吧，无形的大手！\"");
        REGISTRATE.addRawLang("item.spudiversity.emerald_potato.tooltip_1", "吃下后获得「村庄太阳」效果");
        REGISTRATE.addRawLang("item.spudiversity.emerald_potato.tooltip_2", "效果期间村民交易价格降低80%");

        REGISTRATE.addRawLang("item.spudiversity.enchanted_potato.tooltip", "\"这很昂贵\"");
        REGISTRATE.addRawLang("item.spudiversity.enchanted_potato.tooltip_1", "吃下后获得附魔金苹果同款效果");

        REGISTRATE.addRawLang("item.spudiversity.netherite_potato.tooltip", "\"奢侈的享受\"");
        REGISTRATE.addRawLang("item.spudiversity.netherite_potato.tooltip_1", "吃下后获得「抗性提升4」效果");
        REGISTRATE.addRawLang("item.spudiversity.netherite_potato.tooltip_2", "持续1分钟");

        REGISTRATE.addRawLang("item.spudiversity.nether_star_potato.tooltip", "\"这是土豆的巅峰！\"");
        REGISTRATE.addRawLang("item.spudiversity.nether_star_potato.tooltip_1", "吃下后获得2级信标的所有效果");
        REGISTRATE.addRawLang("item.spudiversity.nether_star_potato.tooltip_2", "持续3分钟");

        REGISTRATE.addRawLang("item.spudiversity.apple_potato.tooltip", "\"土豆还是苹果？算了不重要\"");
        REGISTRATE.addRawLang("item.spudiversity.apple_potato.tooltip_1", "挖掘土豆苗概率获取");

        REGISTRATE.addRawLang("item.spudiversity.eyes_potato.tooltip", "\"你最好是土豆\"");
        REGISTRATE.addRawLang("item.spudiversity.eyes_potato.tooltip_1", "食用后清空身上所有药水效果");
        REGISTRATE.addRawLang("item.spudiversity.eyes_potato.tooltip_2", "在那之后获得长时间「夜视」");
        REGISTRATE.addRawLang("item.spudiversity.eyes_potato.tooltip_3", "村民/玩家死亡时概率掉落");

        REGISTRATE.addRawLang("item.spudiversity.ender_potato.tooltip", "\"可以吃的末影之眼\"");
        REGISTRATE.addRawLang("item.spudiversity.ender_potato.tooltip_1", "食用效果同紫颂果");


        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip", "\"明天见！\"");
        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip_0", "按下Shift+右键对准方块可记录该方块上方的坐标");
        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip_1", "食用后可传送到该方块上方");
        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip_2", "记录坐标:X-%s Y-%s Z-%s");
        REGISTRATE.addRawLang("item.spudiversity.tirithippus_potato.tooltip_3", "未记录坐标");

        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip", "\"菲比？菲比啾比\"");
        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip_1", "「时滞屏障」");
        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip_2", "吃下后获得 抗性提升 II（5秒） + 生命恢复 II（2秒）");
        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip_3", "并让半径5格内所有生物（除玩家） 立即陷入「凝时」：");
        REGISTRATE.addRawLang("item.spudiversity.feibu_potato.tooltip_4", "获得缓慢V + 虚弱V");


        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip", "警告！警告！此物品裂变指数超标");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_1", "使用这个传说级土豆后，你将获得超脱之躯：");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_2", "获得伤害吸收V");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_3", "每秒会修复装备耐久");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_4", "自动反击周围敌人");
        REGISTRATE.addRawLang("item.spudiversity.super_potato.tooltip_5", "快来享用吧！");

        REGISTRATE.addRawLang("item.spudiversity.cake_potato.tooltip", "\"生日快乐！\"");
        REGISTRATE.addRawLang("item.spudiversity.cake_potato.tooltip_1", "只是个很好吃的土豆");

        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip", "\"加速吧，直至所有土豆开花结果！\"");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_1", "「土豆」「土豆花」「土豆」「发芽」「土豆」「血舞」");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_2", "「土豆」「育碧大门」「土豆」「服务器」「土豆」");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_3", "「油炸」「炖肉」「土豆」「炙烤」");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_4", "14谜语已至");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_5", "「土豆制造！」");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_6", "穿戴后会快速恢复手持物品耐久");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_7", "同时，你将加速");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_8", "工作台合成：下界之星 ×4、钟 ×3");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_9", "附魔金土豆 ×1、下界之星土豆 ×1");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_10", "特殊进化：佩戴蛋糕王土豆与双色土豆、副手持潮汐土豆时吃下，获得「土豆制造·产自天堂」");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_alt", "按下%s键显示物品描述");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,0), "\" 朕即甜食！众生，为薯献上礼赞！\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,1), "- 「土豆王权!」：永久获得信标所有效果（满级信标）");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,2), "- 「饱腹即是美好」：饥饿值耗尽时自动补充");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,3), "-  「幸福化为羽翼」：获得飞行能力");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,4), "满级信标包含：速度II、急迫II、抗性提升II、跳跃提升II、生命恢复II、力量II；饥饿耗尽时补满至20并回复饱和度5");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,0), "\"额，海盐风味，但是不能吃\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,1), "手持这个土豆时，你的挖掘速度是原来的两倍");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,2), "手持（主副手都行）右键释放球状声波");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,3), "声波没有伤害，但是可以对生物施加缓慢III（3秒） + 发光（10秒）");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.OceanPotato,4), "副手存在镐子且在水中时，声波可以破坏方块");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GreenPotato,0), "\"史蒂夫？不，请叫我释迦摩尼·薯\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GreenPotato,1), "食用后化为打坐状态");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GreenPotato,2), "期间以自身为中心缓慢恢复队友饥饿值");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,0), "W B X!");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,1), "佩戴时获得以下效果：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,2), "「力量2」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,3), "「急迫2」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,4), "「迅捷1」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,5), "「生命恢复1」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MysteriousPotato,6), "攻击打出一道倍率为25%的空气刃");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MadeInPotatoMadeInHaven,1), "现在，加速因它的诞生更加快速");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MadeInPotatoMadeInHaven,2), "此刻，连时间，都可以被加速了");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MadeInPotatoMadeInHaven,3), "按下%s键开始时间加速");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.InfinitePotato,0), "\"无限吃，但是生的\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.InfinitePotatoCooked,0), "\"无限吃，但是熟的\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootKingPotato,0), "\"薯即是财宝本身！\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootKingPotato,1), "佩戴后获得「超级力量」：攻击伤害 ×1.1");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.PotatoSalaed,0), "\"很健康\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.UndyingPotato,0), "\"据说这是唤魔者被流浪商人自愿免费劳动种出来的土豆\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.UndyingPotato,1), "不死图腾同款效果");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.UndyingPotato,2), "流浪商人交易获得");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.HONEY_POTATO,0), "\"甜甜的\"");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SonicPotato,0), "\"怎么感觉这土豆。。。是活得\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SonicPotato,1), "佩戴后按%s发射音波攻击");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BambooPotato,0), "\"据说这是某位竹林间的隐士（或者是一只特别聪明的熊猫？）的杰作，将土豆放入新鲜竹筒中焖烤，让土豆充分吸收了竹子的清香与韧性。\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BambooPotato,1), "食用后，获得 「竹取」 状态");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CookCookedPotato,0), "\"这肯定不能吃了，所以，请你吃~\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CookCookedPotato,1), "可以喂给实体造成魔法伤害（无视无敌帧）");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,0), "「一颗色泽奇异的土豆。表皮是沉默的绛紫，芽眼处却泛起一丝温暖的明黄。」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,1), "「它没有味道，没有香气，握在手中却让人感到一种奇异的平静，仿佛握住了一颗不再跳动的心脏。」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,2), "「你确信它来自一位非常重要的人之手。ta的身影在你记忆中模糊不清，如同隔着一场永不停歇的大雪。唯一清晰的，是ta离去时，你未曾说出口的那句话。」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,3), " 「…食用它。或许你能在其中找到答案，或是遗忘。」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,4), "‘吃了吧，虽然没什么用，但…能顶饱。’");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,5), "一个声音在你脑海里响起，模糊了性别，带着笑意和一丝…难以察觉的疲惫。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,6), "这声音让你感到一阵尖锐的刺痛，如同冰锥刺入太阳穴，但随之而来的却是巨大的、令人窒息的空茫。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,7), "你是谁？ 我曾是谁？");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,8), "我们之间，究竟发生过什么");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NONAME_POTATO,9), "才需要以这样一颗沉默的土豆，来作为最后的锚点，对抗那必将到来的、彻底的遗忘？");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.IcePotato,0), "“他看起来真的就像一块冰一样。额，吃起来也和冰一样”");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.IcePotato,1), "吃掉这土豆，将获得「极寒之力」效果");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.IcePotato,2), "用烤土豆右键雪傀儡加工获得");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BlazePotato,0), "「一颗不再满足于被埋藏于土壤之中的土豆。它渴望热量，渴望蜕变，最终投身于地脉最汹涌的熔岩之中。」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BlazePotato,1), "「炽烈的火焰重塑了它的形骸，沸腾的能量充盈了它的内核」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BlazePotato,2), "「它自火中获得新生」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BlazePotato,3), "吃下后，获得「燃烬」");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SakuraPotato,0), "“一颗经过繁花森林中奇异花粉授粉而诞生的土豆。通体呈现出不同寻常的柔嫩粉白色，表面甚至凝结着几片永远不会枯萎的樱花花瓣。”\n");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SakuraPotato,1), "“它散发着一股清甜的花香，仿佛将整个春天的气息都浓缩在了这小小的块茎之中。”");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SakuraPotato,2), "吃下后，获得「花见狂想」");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EveryThingPotato,0), "「“一枚并非被种植，而是由世界之初的‘可能性’凝结而成的块茎。其表皮是流动的星穹，新芽是闪烁的法则。”」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EveryThingPotato,1), "食用后获得「超脱叙事」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EveryThingPotato,2), "「附录：这个东西无法正常取得」");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,0), "「一颗色泽暗沉、触手冰凉的土豆，躯体布满了血管般的触手」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,1), "「它散发着不祥的气息，拿在手中时，似乎能听到无数的低语」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,2), "食用后获得「空虚之终末」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,3), "「…来，听我说…」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,4), "「…你也很孤独，不是吗？」 ");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,5), "「…你也有无法原谅的人，对吗？」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,6), "「…你也渴望…让这个世界感受到你的痛苦吧？」 ");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TheEndPotato,7), "「…吃下我。我们…合为一体…」");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.Cyclone,0), "「疾风！」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.Cyclone,1), "凋零在200米高度击杀任意生物有30%概率掉落");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.Joker,0), "「王牌！」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.Joker,1), "空杀击杀100血以上的生物概率掉落");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.WaveCore,0), "击杀远古守卫者掉落");


        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.WaveCrystal,0), "");

        REGISTRATE.addRawLang( "effect.spudiversity.copper_potato", "避雷针");
        REGISTRATE.addRawLang( "effect.spudiversity.furnace_blessing", "熔炉祝福");
        REGISTRATE.addRawLang( "effect.spudiversity.village_sun", "村庄太阳");
        REGISTRATE.addRawLang( "effect.spudiversity.no_mobs_booby", "无敌之身");
        REGISTRATE.addRawLang( "effect.spudiversity.for_haven", "天堂制造·天国进行时");
        REGISTRATE.addRawLang( "effect.spudiversity.rain_bow", "禅心");
        REGISTRATE.addRawLang( "effect.spudiversity.bamboo_give", "竹取");
        REGISTRATE.addRawLang( "effect.spudiversity.ice_potato", "极寒之力");
        REGISTRATE.addRawLang( "effect.spudiversity.blaze_end", "燃烬");
        REGISTRATE.addRawLang( "effect.spudiversity.sakura_fantasy", "花见狂想");
        REGISTRATE.addRawLang( "effect.spudiversity.every_thing", "超脱叙事");
        REGISTRATE.addRawLang( "effect.spudiversity.we_are_null", "空虚之终末");
        REGISTRATE.addRawLang( "effect.spudiversity.wave_blood", "流血");
        REGISTRATE.addRawLang( "effect.spudiversity.blood_king", "恐虐");

        REGISTRATE.addRawLang( "stack.spudiversity.blood_king_cd", "「恐虐战意」");

        REGISTRATE.addRawLang( "curios.identifier.potato", "土豆护符");
        REGISTRATE.addRawLang( "curios.modifiers.potato", "作为土豆护符佩戴时");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootKingPotato,2), "同时，你击杀的生物会掉落两次");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NoNamePotatoDark,0), "在凋零被击败的瞬间，你拿在副手的无名土豆失去了所有光泽。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NoNamePotatoDark,1), "变得灰暗、冰冷，如同普通的石块。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NoNamePotatoDark,2), "它耗尽了力量，为你换取了零星的记忆碎片——一个在雪中离去的身影，一句未能说出口的话。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.NoNamePotatoDark,3), "末地坐标0-0-0的念头在你心中浮现。");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,0), "你前往了世界的终点，在坐标的中心");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,1), "你服下了那颗黯淡的土豆，解开了土豆与记忆的最终谜题。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,2), "在理解所有真相后，你不由自主地流下了眼泪");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,3), "那被泪水浸润的土豆残块化为散发永恒光芒的微缩星辰。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,4), "这颗星星，名为「塔弥娜」。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,5), "持有它，你将永不迷失于遗忘。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,6), "她是为你牺牲一切的旅伴，这是她存在过的证明。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,7), "服下吧，%s 你将获得来自她的祝福:不再饥饿，并更加强壮");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,8), "1.来自「Tamina」的祝福：不再饥饿，饱食度始终保持满值");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.StarPotato,9), "在末地中心（0, 0, 0）5格范围内食用「黯淡无光的土豆」获得");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AnnoPotato,0), "\"捏嘿嘿嘿，呀哈哈哈\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AnnoPotato,1), "似乎是从原产地出口又从出口地进口的土豆");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AnnoPotato,2), "糖分蛮高的，减肥期间少吃");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AnnoPotato,3), "使用后获得「生命恢复3」");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,0), "手持右键消耗饱食度发射龙息弹");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,1), "【原料】");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,2), "· 59%的末地石粉尘");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,3), "· 40%的龙口嚼过的紫颂果");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,4), "· 1%的末影龙的口水（因为太好吃）");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,5), "【温馨提示】");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,6), "■若遇到普通土豆投来嫉妒的目光属正常现象");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,7), "■本品最终解释权归末影龙所有（它说下次要往里面加更多龙息）");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,8), "屠龙纪念物品");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EnderDragonPotato,9), "获得成就《解放末地》后获得");

        REGISTRATE.addRawLang( "effect.spudiversity.undead_transformation_effect", "万人嫌");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BambooPotato,2), "拿苹果土豆对正在进食竹子的熊猫右键获得");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.SakuraPotato,3), "在繁花森林生物群系中，对一棵土豆苗使用骨粉，有极小概率获得1-2个");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.rabbit,0), "\"rabbit\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.tank,0), "\"tank\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.rabbit,1), "兔子被爆炸伤害杀死获得");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.tank,1), "海龟被爆炸伤害杀死获得");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.bear_rabbit_tank,0), "\"气泡满满\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.bear_rabbit_tank,1), "你能感觉到这玩意是有汽的");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,0), "\"英雄可不能临阵脱逃啊\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,1), "提供的效果:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,2), "1.超高速I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,3), "2.超高跳I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,4), "3.生命恢复I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,5), "4.抗性提升II");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.BuildPotato,6), "5.近战攻击有30%的概率对周围的敌怪追加一次爆炸伤害（爆炸伤害不能触发）");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,0), "\"早安！土豆！\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,1), "据说非常的强劲");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,2), "穿戴后获得抗性提升1、夜视和力量1");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,3), "击杀「凋零」后额外获得生命恢复1和生命提升1");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,4), "击杀「远古守卫」后额外获得水下呼吸和跳跃提升");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,5), "击杀「末影龙」后额外获得迅捷，伤害吸收和缓降");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,6), "击杀「监守者」后额外通过zzz土豆获得的抗性提升和力量的等级+1");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,7), "此外击杀「监守者」后攻击附带失明，和缓慢。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.ZzzPotato,8), "手持下界之星土豆右键床获得");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,0), "\"Here we go！！！！\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,1), "提供的效果:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,2), "1.抗性提升II");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,3), "2.伤害吸收I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,4), "3.饱和I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,5), "4.力量I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,6), "5.速度I");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,7), "6.夜视");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,8), "7.穿戴后周围的狗获得力量效果");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,9), "8.免疫黑暗");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GeedPotato,10), "暂时无法获得");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootPotato,0), "\"华丽，这钻石的光泽便是薯的尊贵！\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootPotato,1), "佩戴这个土豆造成击杀后视为击杀两次");

        REGISTRATE.addRawLang( "effect.spudiversity.glass_type", "玻璃行走");
        REGISTRATE.addRawLang( "effect.spudiversity.radiant_light", "荧光");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlassPotato,0), "\"好难嚼，像玻璃\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlassPotato,1), "食用后获得「玻璃行走」效果");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlassPotato,2), "效果持续期间可以在玻璃中行动，你的投射物可以穿过玻璃");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlassPotato,3), "要注意的是，你也会如同玻璃一般易碎");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlowStonePotato,0), "\"感觉牙齿在发光\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlowStonePotato,1), "食用后获得「荧光」效果");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.GlowStonePotato,2), "你浑身发着光");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TarianPotato,0), "「听，末地破碎，出生点生灵涂炭的声音」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TarianPotato,1), "食用后触发:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TarianPotato,2), "「自我中心型瞬爆流星一条」");


        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.Potato36,0), "「Potato安全卫士为您服务」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.Potato36,1), "佩戴后获得以下效果：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.Potato36,2), "1.每1%内存占有率造成的伤害提升 1%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.Potato36,3), "2.攻击有25%的概率触发「伤害强效清理」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.Potato36,4), "3.非魔法、火焰攻击造成伤害会追加一段5%的魔法伤害");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.FireworkPotato,0), "「烟花的轨迹，也是薯的华丽」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.FireworkPotato,1), "佩戴后获得以下效果：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.FireworkPotato,2), "拉弓发射箭矢后会追加两个烟花火箭");

        REGISTRATE.addRawLang("item.spudiversity.alchemist_potato.tooltip_alt", "按下%s查看存储的药水效果");

        REGISTRATE.addRawLang("tooltip.alchemist_potato.stored", "§7已注入的药水：");
        REGISTRATE.addRawLang("tooltip.alchemist_potato.empty", "无");
        REGISTRATE.addRawLang("message.alchemist_potato.inject_success", "§a药水已成功注入土豆！");
        REGISTRATE.addRawLang("message.alchemist_potato.full","§c土豆已满！无法继续注入药水。");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AlchemistPotato,0), "「酿造 铸就 薯来炼金！」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AlchemistPotato,1), "副手手持这个土豆主手手持喷溅型药水右键可以注入最多10个");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.AlchemistPotato,2), "穿戴饰品后每次攻击都会从10个注入位中选一个作用给目标");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.DiamondCrystalPotato,0), "「尊贵的水晶，薯中的vip！」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.DiamondCrystalPotato,1), "食用后获得:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.DiamondCrystalPotato,2), "1.抗性提升4");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.DiamondCrystalPotato,3), "2.力量2");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.DiamondCrystalPotato,4), "3.生命恢复3");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.DiamondCrystalPotato,5), "手持钻石土豆右击紫水晶母岩获得");

        REGISTRATE.addRawLang( "effect.spudiversity.invisible_waiting", "不可见的守候");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,0), "「但愿我能让你骄傲，但愿我没让你失望」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,1), "「……别……难过……」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,2), "似乎是某个女孩在启用某物前留下的信息被土豆融合产生的特殊土豆");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,3), "佩戴后获得以下效果：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,4), "1.你可以进行创造飞行");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,5), "2.持续获取「不可见的守候」效果");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,6), "3.可以额外装备一个「土豆护符」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EdelweissPotato,7), "4.死亡时免死一次（回复满血，冷却120秒）");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EmberPotato,0), "「燃烧直到黎明时」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.EmberPotato,1), "食用后获得效果「以此燃至黎明」");

        REGISTRATE.addRawLang( "effect.spudiversity.burn_with_dawn", "以此燃至黎明");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TinyWaterPotato,0), "「救命啊，这条鱼快被空气淹死了」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TinyWaterPotato,1), "1.在水/雨中可进行创造飞行");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TinyWaterPotato,2), "2.在水中时获得夜视 II、水下呼吸与海豚的恩惠 II");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TinyWaterPotato,3), "3.位于水/雨中时，每秒恢复1颗心生命值，并获得 +5 攻击伤害加成");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TinyWaterPotato,4), "4.处于沙漠/恶地/下界时会持续扣血");






        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,0), "「看见这颗土豆，你已感受到，那支配寰宇的力量的一部分」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,1), "食用后永久生效：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,2), "1.无尽的力量将使你%s你的攻击力大幅提升");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,3), "2.你的位格得到了提升，所有位格低于你的生物对你造成的伤害都会被%s");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,4), "3.你得到了那把剑的一丝力量，你的近战伤害将会同步进行伪典的%s");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,5), "4.无尽的生机让你彻底%s了升格的身体，你生命力大幅提升");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,6), "你的贪婪...无穷无尽");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,7), "1.攻击力提升至基础值的 6 倍（+500%）");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,8), "2.受到的所有伤害减少 50%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,9), "3.近战命中时同步造成一次等额真实伤害（约伤害翻倍），并重置目标无敌帧");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.UniversePotato,10), "4.最大生命值提升至基础值的 6 倍（+500%）");

        REGISTRATE.addRawLang("tooltip.spudiversity.entropy_increase","熵增");
        REGISTRATE.addRawLang("tooltip.spudiversity.suppress","压制");
        REGISTRATE.addRawLang("tooltip.spudiversity.endless_judgment","无尽裁决");
        REGISTRATE.addRawLang("tooltip.spudiversity.dominate","支配");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CrystalPotato,0), "「早安！今天的你，也一定闪闪发光～」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CrystalPotato,1), "1.幸运 +10");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CrystalPotato,2), "2.生命上限 +10");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CrystalPotato,3), "3.受到即死伤害时，会优先清空饱食度来抵消此次伤害");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CrystalPotato,4), "手持【樱吹雪土豆】点击床获得");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.NeutroniumPotato,0), "「如此的沉重。。。」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.NeutroniumPotato,1), "佩戴后：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.NeutroniumPotato,2), "1.时运和抢夺等级+5");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpAvaPotato.NeutroniumPotato,3), "2.攻击施加缓慢5和虚弱5");

        REGISTRATE.addRawLang( "effect.spudiversity.misty", "雾化");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MistyPotato,0), "「我是阿尔法瑞薯」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MistyPotato,1), "食用后获得「雾化」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MistyPotato,2), "「雾化」期间除了boss以外的生物不会锁定你");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.MistyPotato,3), "同时按下shift即可穿墙");

        REGISTRATE.addRawLang( "singularity.spudiversity.poisonous_potato", "新生的土豆");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,0), "「她把温柔留给了你，把火焰留给了自己。现在，火焰也属于你了——连同她所有的痛苦，和那未曾说出口的爱」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,1), "占用独立永续槽位，不与其他永续土豆冲突：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,2), "1.攻击力+8");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,3), "2.所有攻击附加火焰附加 III，且点燃时间延长至15秒");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,4), "3.造成的伤害无视目标的护甲");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,5), "当你的永续土豆是「星辰土豆」时:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,6), "1.你可以创造飞行");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,7), "2.「气泡兔坦土豆」的连环爆炸概率变为100%");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,8), "当你的死亡次数>100时:");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,9), "佩戴「蛋糕王土豆」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.TangxingPotato,10), "副手手持「未烬的核」食用樱吹雪土豆获得");









        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,0), "「Thank you for your visit」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,1), "佩戴后获得以下效果：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,2), "1.受到伤害降低25%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,3), "2.每拥有一种正面效果，则：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,4), "· 攻击伤害提高2%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,5), "· 护甲韧性提高1");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,6), "· 最大生命提高10%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,7), "3.将攻击造成伤害的20%转化为自身治疗");


        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,8), "我曾以为，她留下的仅有两颗土豆：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,9), "一颗温柔地照亮我的前路，");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,10), "另一颗在无人看见的远方独自燃烧。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,11), "如今雨止天晴，天边那片晴透的蓝，");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,12), "是她终于可以安静看着我的颜色——");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,13), "That is her last wish.");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,14), "“别找了，我一直在，在天晴的地方。”");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,15), "在你游玩时长到达99小时以上后");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.JijinPotato,16), "佩戴「蛋糕王土豆」并食用「棠星土豆」获得");

        REGISTRATE.addRawLang("item.spudiversity.jijin_potato.tooltip_alt", "按下「%s」查看故事");
        REGISTRATE.addRawLang("item.spudiversity.jijin_potato.tooltip_shift", "按下「%s」查看获取");

        REGISTRATE.addRawLang("item.spudiversity.upgrade_smithing_template", "雪绒模板");
        REGISTRATE.addRawLang("item.spudiversity.upgrade_smithing_template_use", "制作雪绒土豆");
        REGISTRATE.addRawLang("item.spudiversity.upgrade_smithing_template_need", "纸飞机");


        REGISTRATE.addRawLang("message.spudiversity.lose", "大失败！你的攻击减少并受到反噬");
        REGISTRATE.addRawLang("message.spudiversity.happy", "成功！你的伤害增加20%");
        REGISTRATE.addRawLang("message.spudiversity.very_happy", "大成功！你的伤害增加30%");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.the_end_core,0), "「她所有的痛苦与火焰藏在了这个核中」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpItem.the_end_core,1), "副手手持下界之星后在下界食用星辰土豆获得");



    }



    public static String FastMakeItemTooltip(RegistryEntry<Item, ? extends Item> item,int nummber){
        if (nummber ==0){
        return "item.spudiversity." + item.getId().getPath() + ".tooltip";
        }
    return "item.spudiversity." + item.getId().getPath() + ".tooltip_" + nummber;
    }
}
