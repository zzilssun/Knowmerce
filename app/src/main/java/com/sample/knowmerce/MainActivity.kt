package com.sample.knowmerce

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.net.toUri
import com.sample.knowmerce.feature.main.search.SearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            SearchScreen(
                handleToast = { message ->
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                },
                onClickFab = {
                    ArchiveActivity.startActivity(this)
                },
                onClickContent = { link ->
                    runCatching {
                        startActivity(Intent(Intent.ACTION_VIEW, link.toUri()))
                    }
                },
            )
        }
    }
}