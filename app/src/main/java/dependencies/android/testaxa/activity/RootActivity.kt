package dependencies.android.testaxa.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.testaxa.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class RootActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_layout)
    }
}