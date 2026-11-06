package io.turbo.aAWPkt.command

import io.turbo.aAWPkt.AAWPkt
import org.incendo.cloud.annotations.AnnotationParser
import org.incendo.cloud.execution.ExecutionCoordinator
import org.incendo.cloud.paper.PaperCommandManager
import org.incendo.cloud.paper.util.sender.PaperSimpleSenderMapper
import org.incendo.cloud.paper.util.sender.Source

object CommandService {

    private lateinit var manager: PaperCommandManager<Source>
    private val commands = listOf("")

    fun configure() {
        this.manager =  PaperCommandManager.builder(PaperSimpleSenderMapper.simpleSenderMapper())
            .executionCoordinator(ExecutionCoordinator.simpleCoordinator())
            .buildOnEnable(AAWPkt)
        AnnotationParser(manager, Source::class.java)
            .parse(WarpCommand)

    }
}