package io.github.maloryware.magmaphile;

import eu.midnightdust.lib.config.MidnightConfig;

public class Config extends MidnightConfig {

    public static final String GENERAL = "general";

    @Entry(category = GENERAL) public static boolean override_lava_gen;
    @Entry(category = GENERAL, min = -255) public static int lava_max_height_gen;
    @Entry(category = GENERAL) public static boolean toggle_lower_bound;
    @Entry(category = GENERAL, min = -255) public static int lava_min_height_gen;




}
