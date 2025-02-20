package io.github.maloryware.magmaphile;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO: sort out mixinextras related issues

public class Magmaphile implements ModInitializer {
	public static final String MOD_ID = "magmaphile";


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Magmaphile initializing...");
		MidnightConfig.init(MOD_ID,Config.class);
		LOGGER.info("Magmaphile initialized!");
	}
}