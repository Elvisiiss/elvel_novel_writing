CREATE TABLE `business_books`
(
    `id`                    int          NOT NULL AUTO_INCREMENT COMMENT '书籍ID',
    `title`                 varchar(200) NOT NULL COMMENT '书名',
    `cover_url`             varchar(255)                                         DEFAULT NULL COMMENT '封面URL',
    `description`           text COMMENT '书籍描述',
    `genre`                 varchar(50)                                          DEFAULT NULL COMMENT '分类/体裁',
    `tags`                  json                                                 DEFAULT NULL COMMENT '标签数组',
    `user_id`               int          NOT NULL COMMENT '作者用户ID',
    `base_version_id`       int                                                  DEFAULT NULL COMMENT '基于的版本ID（衍生作品来源）',
    `version_code`          varchar(20)                                          DEFAULT 'v1' COMMENT '版本号',
    `status`                enum ('draft','serializing','completed','abandoned') DEFAULT 'draft' COMMENT '状态：草稿/连载中/已完成/废弃',
    `derivative_permission` enum ('open','require_auth','closed')                DEFAULT 'open' COMMENT '衍生权限：开放/需授权/关闭',
    `word_count`            int                                                  DEFAULT '0' COMMENT '总字数',
    `chapter_count`         int                                                  DEFAULT '0' COMMENT '章节数',
    `read_count`            int                                                  DEFAULT '0' COMMENT '阅读次数',
    `like_count`            int                                                  DEFAULT '0' COMMENT '点赞数',
    `create_time`           timestamp    NULL                                    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`           timestamp    NULL                                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='书籍主表';

CREATE TABLE `business_scenes`
(
    `id`                   int          NOT NULL AUTO_INCREMENT COMMENT '场景ID',
    `book_id`              int          NOT NULL COMMENT '书籍ID',
    `name`                 varchar(200) NOT NULL COMMENT '场景名称',
    `description`          text COMMENT '场景描述',
    `type`                 enum ('location','event','atmosphere') DEFAULT 'location' COMMENT '类型：地点/事件/氛围',
    `details`              json                                   DEFAULT NULL COMMENT '场景详情（JSON格式）',
    `first_appear_chapter` int                                    DEFAULT NULL COMMENT '首次出现章节',
    `create_time`          timestamp    NULL                      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='场景表';

CREATE TABLE `business_chapters`
(
    `id`               int       NOT NULL AUTO_INCREMENT COMMENT '章节ID',
    `book_id`          int       NOT NULL COMMENT '书籍ID',
    `chapter_number`   int       NOT NULL COMMENT '章节序号',
    `title`            varchar(200)   DEFAULT NULL COMMENT '章节标题',
    `content_summary`  text COMMENT '章节摘要（AI生成）',
    `word_count`       int            DEFAULT '0' COMMENT '章节字数',
    `generation_count` int            DEFAULT '0' COMMENT '生成次数',
    `is_completed`     tinyint(1)     DEFAULT '0' COMMENT '是否已完成',
    `create_time`      timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_book_chapter` (`book_id`, `chapter_number`) COMMENT '书籍章节唯一索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='章节表';

CREATE TABLE `business_comments`
(
    `id`                int       NOT NULL AUTO_INCREMENT COMMENT '评论ID',
    `user_id`           int       NOT NULL COMMENT '用户ID',
    `book_id`           int       NOT NULL COMMENT '书籍ID',
    `target_type`       enum ('book','chapter','generation') DEFAULT 'book' COMMENT '评论目标类型：书籍/章节/生成内容',
    `target_id`         int       NOT NULL COMMENT '目标ID',
    `content`           text      NOT NULL COMMENT '评论内容',
    `parent_comment_id` int                                  DEFAULT NULL COMMENT '父评论ID',
    `like_count`        int                                  DEFAULT '0' COMMENT '点赞数',
    `create_time`       timestamp NULL                       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='评论表';

CREATE TABLE `business_characters`
(
    `id`          int          NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `book_id`     int          NOT NULL COMMENT '书籍ID',
    `name`        varchar(100) NOT NULL COMMENT '角色名称',
    `description` text COMMENT '角色描述',
    `traits`      json                               DEFAULT NULL COMMENT '角色特征（JSON格式）',
    `appearance`  text COMMENT '外貌描述',
    `importance`  enum ('main','supporting','minor') DEFAULT 'minor' COMMENT '重要性：主角/配角/次要角色',
    `create_time` timestamp    NULL                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp    NULL                  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色表';

CREATE TABLE `business_book_outlines`
(
    `id`           int       NOT NULL AUTO_INCREMENT COMMENT '大纲ID',
    `book_id`      int       NOT NULL COMMENT '书籍ID',
    `outline_text` text      NOT NULL COMMENT '大纲内容',
    `structure`    json           DEFAULT NULL COMMENT '大纲结构（JSON格式）',
    `version`      int            DEFAULT '1' COMMENT '大纲版本',
    `create_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='书籍大纲表';

CREATE TABLE `business_decision_points`
(
    `id`            int       NOT NULL AUTO_INCREMENT COMMENT '决策点ID',
    `chapter_id`    int       NOT NULL COMMENT '章节ID',
    `generation_id` int       NOT NULL COMMENT '生成记录ID（在哪个生成后出现）',
    `position`      int       NOT NULL COMMENT '在章节中的位置',
    `question`      text COMMENT '决策问题',
    `create_time`   timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='决策点表';

CREATE TABLE `business_reading_records`
(
    `id`                 int       NOT NULL AUTO_INCREMENT COMMENT '阅读记录ID',
    `user_id`            int       NOT NULL COMMENT '用户ID',
    `book_id`            int       NOT NULL COMMENT '书籍ID',
    `chapter_id`         int       NOT NULL COMMENT '章节ID',
    `last_generation_id` int       NOT NULL COMMENT '最后阅读的生成记录ID',
    `reading_mode`       enum ('immersion','creation','god') DEFAULT 'immersion' COMMENT '阅读模式：沉浸/创作/上帝视角',
    `progress`           float                               DEFAULT '0' COMMENT '阅读进度（0-1）',
    `last_read_at`       timestamp NULL                      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后阅读时间',
    `create_time`        timestamp NULL                      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_book` (`user_id`, `book_id`) COMMENT '用户书籍唯一索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='阅读记录表';

CREATE TABLE `business_decision_options`
(
    `id`                 int       NOT NULL AUTO_INCREMENT COMMENT '选项ID',
    `decision_point_id`  int       NOT NULL COMMENT '决策点ID',
    `option_text`        text      NOT NULL COMMENT '选项描述',
    `is_system_provided` tinyint(1)     DEFAULT '1' COMMENT '是否为系统提供（否则为用户自定义）',
    `keywords`           json           DEFAULT NULL COMMENT '关键词数组',
    `selected_count`     int            DEFAULT '0' COMMENT '被选择次数（统计用）',
    `is_selected`        tinyint(1)     DEFAULT '0' COMMENT '是否被创作者选中',
    `next_generation_id` int            DEFAULT NULL COMMENT '选择后生成的内容ID',
    `create_time`        timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='决策选项表';

CREATE TABLE `business_creation_sessions`
(
    `id`                        int       NOT NULL AUTO_INCREMENT COMMENT '会话ID',
    `user_id`                   int       NOT NULL COMMENT '用户ID',
    `book_id`                   int       NOT NULL COMMENT '书籍ID',
    `chapter_id`                int       NOT NULL COMMENT '章节ID',
    `current_generation_id`     int            DEFAULT NULL COMMENT '当前生成记录ID',
    `pending_decision_point_id` int            DEFAULT NULL COMMENT '待处理决策点ID',
    `session_data`              json           DEFAULT NULL COMMENT '会话状态数据（JSON格式）',
    `is_active`                 tinyint(1)     DEFAULT '1' COMMENT '是否活跃',
    `create_time`               timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_activity_at`          timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后活动时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='创作会话表';

CREATE TABLE `business_content_generations`
(
    `id`                int       NOT NULL AUTO_INCREMENT COMMENT '生成记录ID',
    `chapter_id`        int       NOT NULL COMMENT '章节ID',
    `generation_index`  int       NOT NULL COMMENT '生成序号（在章节中的顺序）',
    `content`           text      NOT NULL COMMENT '生成内容',
    `prompt`            text COMMENT '使用的提示词',
    `decision_point_id` int            DEFAULT NULL COMMENT '基于的决策点ID',
    `ai_model`          varchar(50)    DEFAULT NULL COMMENT '使用的AI模型',
    `token_count`       int            DEFAULT NULL COMMENT '消耗的token数',
    `is_rewritten`      tinyint(1)     DEFAULT '0' COMMENT '是否重写过',
    `create_time`       timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_chapter_generation` (`chapter_id`, `generation_index`) COMMENT '章节生成顺序唯一索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='内容生成记录表';

CREATE TABLE `business_version_relationships`
(
    `id`                      int       NOT NULL AUTO_INCREMENT COMMENT '关系ID',
    `original_book_id`        int       NOT NULL COMMENT '原始书籍ID',
    `derivative_book_id`      int       NOT NULL COMMENT '衍生书籍ID',
    `fork_from_chapter_id`    int                        DEFAULT NULL COMMENT '分叉来源章节ID',
    `fork_from_generation_id` int                        DEFAULT NULL COMMENT '分叉来源生成记录ID',
    `relationship_type`       enum ('direct','indirect') DEFAULT 'direct' COMMENT '关系类型：直接/间接',
    `permission_granted`      tinyint(1)                 DEFAULT '1' COMMENT '是否获得授权',
    `create_time`             timestamp NULL             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_version_relation` (`original_book_id`, `derivative_book_id`) COMMENT '版本关系唯一索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='版本关系表';
