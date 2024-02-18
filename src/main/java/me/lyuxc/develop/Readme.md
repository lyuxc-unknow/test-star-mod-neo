```text
│  Star.java  主类
│  Tiers.java  工具等级
│  Variables.java  变量
│
├─block
│  │  BlockRegistry.java  方块注册
│  │
│  └─blocks
│          FanBlock.java  假的风扇
│
├─datagen
│      BlockStateProviders.java  方块状态生成
│      BlockTagsProviders.java  标签生成
│      DataGeneration.java  生成器
│      ItemModelProviders.java  物品模型生成
│      LanguageProviders.java  语言生成
│
├─event
│      onChat.java  当聊天时事件
│      onCommand.java 用指令时事件
│      onCommandRegistry.java  注册指令
│      onGuiInit.java  GUI初始化时
│      onItemToolTip.java  物品提示
│      onMobSplit.java  怪物分裂
│      onMouseInput.java  鼠标输入（禁用快速连点）
│      onPlayerInteract.java  玩家交互
│      onPlayerLogging.java  玩家登录
│      onPlayerRespawn.java  玩家重生
│      onTickEvent.java  每Tick事件
│
├─item
│  │  ItemRegistry.java  物品注册
│  │
│  ├─items
│  │      AlphaMedicalBox.java  阿尔法医疗箱
│  │      EndItem.java  结束物品
│  │      GazeOfCapital.java  资本目光
│  │      GravitationalMagneticField.java 重力磁场
│  │      Light.java  雷电权杖
│  │      MultiPlayerTool.java  解锁多人模式的东西
│  │      SpiritualFood.java  精神食粮
│  │      WaterGetter.java  获取水的东西
│  │
│  └─tools
│          Level1Sword.java --|
│          Level2Sword.java   |
│          Level3Sword.java   |
│          Level4Sword.java   |---等级剑
│          Level5Sword.java   |
│          Level6Sword.java   |
│          Level7Sword.java   |
│          Level8Sword.java --|
│          MyBow.java  结束游戏的弓（没啥用，占位置）
│          MySword.java  神的剑
│          TetanusBlade.java  破伤风之刃（吸血剑）
│          WoodShears.java  木剪刀
│
├─mixins
│  │  MixinAdvancementToast.java  成就弹窗
│  │  MixinBaseFireBlock.java   地狱门可以在末地
│  │  MixinGameTab.java  创建世界时锁定一些东西
│  │  MixinMinecraft.java  改标题
│  │  MixinOptionScreen.java  难度再次锁定不可改
│  │  MixinTitleScreen.java  主界面是否解锁多人
│  │
│  └─disableExperimentus                
│          MixinPrimaryLevelData.java --|
│          MixinWorldListEntity.java    |--禁用实验性设置（复制粘贴的）
│          MixinWorldOpenFlows.java   --|
│
└─utils
CommandExecutes.java  注册指令时要干的事情
FileUtils.java  文件读入写入
TextUtils.java  从从无尽贪婪复制的代码，用于实现彩色字体
Utils.java  杂七杂八的东西都放这里
```