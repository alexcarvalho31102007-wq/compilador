package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.BuildStageItem
import com.example.data.model.GitHubConfig
import com.example.data.model.StageStatus
import com.example.data.repository.CompilerRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Compilador", appName)
    }

    @Test
    fun `github config verification`() {
        val config = GitHubConfig(
            token = "ghp_12345",
            owner = "alex",
            repo = CompilerRepository.DEFAULT_REPO_NAME
        )
        assertTrue(config.isConfigured)
        assertEquals("alex/app-compiler-builds", config.repoFullName)
    }

    @Test
    fun `stage status defaults to pending`() {
        val stage = BuildStageItem(
            id = 1,
            title = "Validação",
            description = "Validação do ZIP"
        )
        assertEquals(StageStatus.PENDING, stage.status)
    }

    @Test
    fun `workflow default content contains assemble command`() {
        val content = CompilerRepository.DEFAULT_WORKFLOW_CONTENT
        assertTrue(content.contains("assembleRelease"))
        assertTrue(content.contains("upload-artifact"))
    }
}
