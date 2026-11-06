package io.turbo.aAWPkt.config

import de.exlll.configlib.Configuration
import de.exlll.configlib.NameFormatters
import de.exlll.configlib.YamlConfigurationProperties
import de.exlll.configlib.YamlConfigurations
import io.turbo.aAWPkt.AAWPkt
import io.turbo.aAWPkt.warp.types.Warp
import java.io.File
import java.nio.charset.StandardCharsets

@Configuration
class Config {


    var warps: MutableList<Warp> = mutableListOf()

    companion object {
        private lateinit var _instance: Config

        val properties: YamlConfigurationProperties = YamlConfigurationProperties.newBuilder()
            .charset(StandardCharsets.UTF_8)
            .setNameFormatter(NameFormatters.LOWER_KEBAB_CASE).build()

        fun reload() {
            _instance = YamlConfigurations.load(
                File(AAWPkt.dataFolder, "config.yml").toPath(),
                Config::class.java,
                properties
            )
        }

        fun instance(): Config {
            if (::_instance.isInitialized) {
                _instance = YamlConfigurations.update(
                    File(AAWPkt.dataFolder, "config.yml").toPath(),
                    Config::class.java,
                    properties
                )
            }
            return _instance
        }
    }
}