package com.sample.knowmerce

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.net.toUri
import com.sample.knowmerce.feature.main.archive.ArchiveScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArchiveActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ArchiveScreen(
                onFinish = ::finish,
                onClickContent = { link ->
                    runCatching {
                        startActivity(Intent(Intent.ACTION_VIEW, link.toUri()))
                    }
                },
            )
        }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ArchiveActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            })
        }
    }
}