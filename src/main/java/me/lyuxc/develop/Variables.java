package me.lyuxc.develop;

import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Variables {
    //模组名
    public static final String MOD_ID = "test_star";
    //最大玩家血量
    public static double MAX_HEALTH = 80;
    //开发者设置 - 名字
    public static final String DEVELOPER_NAME = "lyuxc_";
    //开发者设置 - 标签
    public static boolean DEVELOPER = false;
    //开发者密钥 - AES - developer
    public static final String DEVELOPER_KEY = "U2FsdGVkX1+llhDgAi3Cj148i4V6l3nR4aEs0nrPmAKStn9NV9rYBN5TOA3LUCR7";
    //创造模式切换 - 密钥
    public static final String CREATIVE_KEY = "{\"gameMode: \"5Yib6YCg\", \"key\": \"{developer_key}\"}".replace("{developer_key}", Base64.getEncoder().encodeToString(DEVELOPER_KEY.getBytes()));
    //列表 - 禁用命令
    public static String[] DISABLE_COMMAND = {
            "gamemode", "give", "attribute", "advancement", "difficulty", "effect", "fill", "gamerule",
            "item", "loot", "place", "setblock", "summon", "teleport", "tp", "weather", "xp"
    };
    //最大模组数
    public static final int MAX_MOD_COUNT = 1024;
    //随机 - 种子：时间刻
    public static Random random = new Random(System.currentTimeMillis());
    //日期
    public static final Calendar calendar = Calendar.getInstance();
    //根据日获取随机种子
    public static Random Random_Day = new Random(calendar.get(Calendar.DAY_OF_YEAR));
    //获取MC运行路径
    public static String workDir = System.getProperty("user.dir");
    //多人模式配置名称
    public static String configDir = "multiplayerUnlock.MCL";
    //获取到的数据 - 预留
    public static String data = "";
    //禁用方块 - id
    public static String[] IDs;
    //标题
    public static String title = "Mind2用户端";
    //日志
    public static final Logger LOGGER = LogManager.getLogger("ModLoader");
    //MC
    public static Minecraft mc = null;
    public static Map<UUID, Integer> playerLeftClicksMap = new HashMap<>();
    public static Map<UUID, Integer> playerRightClicksMap = new HashMap<>();
}
