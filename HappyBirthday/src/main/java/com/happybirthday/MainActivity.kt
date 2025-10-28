package com.happybirthday
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import com.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : FragmentActivity() { // changed to FragmentActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Put the Fragment-hosting AndroidView inside the scaffold content
                    FragmentHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun FragmentHost(modifier: Modifier = Modifier) {
    // AndroidView will create a FragmentContainerView and the fragment will be
    // inserted into it via supportFragmentManager.
    Box(modifier = modifier.fillMaxSize()) {
        val context = LocalContext.current
        AndroidView(
            factory = { ctx ->
                // create the container view programmatically
                FragmentContainerView(ctx).apply {
                    id = View.generateViewId() // generate unique id for fragment manager
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            update = { containerView ->
                // Add fragment only if not already present (avoids duplicates)
                val fm = (context as FragmentActivity).supportFragmentManager
                if (fm.findFragmentById(containerView.id) == null) {
                    fm.beginTransaction()
                        .replace(containerView.id, BirthdayCardFragment())
                        .commit()
                }
            }
        )
    }
}
