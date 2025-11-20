package com.dinzeer.spudiversity.util.lang;

import com.dinzeer.spudiversity.regsitry.SpGodFoxPotato;
import com.dinzeer.spudiversity.regsitry.SpICFPotato;
import com.dinzeer.spudiversity.regsitry.SpItem;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

import static com.dinzeer.spudiversity.Spudiversity.REGISTRATE;

public class CnLangUtil {
    public static void init(){
        REGISTRATE.addRawLang("item_group.spudiversity.spudiversity", "土豆盛宴");

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
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_8", "此外，当你背负蛋糕之王与双色口味");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_9", "并将海洋的土豆放在你的副手");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_10", "吃下它，你将会得到，真正的「伟力」");
        REGISTRATE.addRawLang("item.spudiversity.made_in_potato.tooltip_alt", "按下%s键显示物品描述");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,0), "\" 朕即甜食！众生，为薯献上礼赞！\"");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,1), "- 「土豆王权!」：永久获得信标所有效果（满级信标）");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,2), "- 「饱腹即是美好」：饥饿值耗尽时自动补充");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.CakeKingPotato,3), "-  「幸福化为羽翼」：获得飞行能力");

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
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpPotato.LootKingPotato,1), "佩戴这个土豆会获得「超级力量」");

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

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,0), "「由火龙的血液铸就的龙血土豆」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,1), "吃下后，成为「火龙」的土豆裔");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,2), "你将获得：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,3), "岩浆块行者");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,4), "接触到没变成岩浆块的熔岩会获得急迫 I效果");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,5), "拥有常驻抗火效果");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,6), "可以自动冶炼");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,7), "生命上限提高50%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.FireDragonPotato,8), "以你为中心半径5格内的水会被蒸发");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,0), "「由冰龙的血液铸就的龙血土豆」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,1), "吃下后，成为「冰龙」的土豆裔");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,2), "你将获得：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,3), "冰霜行者");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,4), "免疫缓慢效果");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,5), "攻击生物，可将其冻结在原地3秒（对BOSS无效），并清除其身上的火焰效果");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,6), "在水中的最终伤害增加15%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.IceDragonPotato,7), "生命上限提高50%");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,0), "「由电龙的血液铸就的龙血土豆」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,1), "吃下后，成为「电龙」的土豆裔");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,2), "你将获得：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,3), "有几率在攻击命中的生物头顶召唤一道闪电");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,4), "在雷暴天气下或附近有避雷针的情况下雷劈几率大幅提升");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,5), "移动时会积累静电，满层后下一次攻击将对小范围敌人造成击退与麻痹（失明+缓慢）效果。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,6), "在非晴天时移动获得速度II提升。");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,7), "生命上限提高50%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpICFPotato.LightningDragonPotato,8), "生命值越低，你的攻击速度与移动速度越快（最高提升25%）");


        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,0), "「从神狐那里获得的赐福土豆，口感香甜，同时带在身上也很早不错」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,1), "佩戴时：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,2), "当饱食度满值时提供18%的治疗加成");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,3), "根据幸运值的18%提供减伤");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,4), "根据幸运值的18%提供增伤");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,5), "佩戴饰品的同时包内含有御神刀「神狐」会持续获取“村庄太阳”效果");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.SkyFoxPotato,6), "使用绿宝石土豆敲击御神刀「神狐」获得");

        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,0), "「从血狐那里获得的赐福土豆，口感辛辣，戴在身上感觉很难受」");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,1), "佩戴时：");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,2), "你的近战伤害变成火焰伤害");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,3), "命中时，目标将陷入持续3秒的「流血」效果，每秒受到2点伤害。此效果不可叠加，但会刷新持续时间");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,4), "你对「流血」状态的生物伤害增加40%");
        REGISTRATE.addRawLang(FastMakeItemTooltip(SpGodFoxPotato.BloodFoxPotato,5), "当连续击杀10个及以上敌人，玩家进入持续5秒的「恐虐」状态");

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
    }



    public static String FastMakeItemTooltip(RegistryEntry<? extends Item> item,int num){
        if (num ==0){
        return "item.spudiversity." + item.getId().getPath() + ".tooltip";
        }
    return "item.spudiversity." + item.getId().getPath() + ".tooltip_" + num;
    }
}
