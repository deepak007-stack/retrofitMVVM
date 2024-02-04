package com.example.retrofitmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitmvvm.model.PostItem
import com.example.retrofitmvvm.repository.UserRepository
import com.example.retrofitmvvm.ui.theme.RetrofitMVVMTheme
import com.example.retrofitmvvm.viewmodels.MainViewModel
import com.example.retrofitmvvm.viewmodels.MainViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitMVVMTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val apiService = RetrofitReq.service
                    val repository = UserRepository(apiService)
                    mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]

//                    mainViewModel.post.observe(this, Observer{
//                        Log.d("test", it.get(0).toString())
//                    })

                    val dataList by mainViewModel.post.observeAsState(initial = emptyList())

                    MyList(dataList)
                }
            }
        }
    }
}

@Composable
fun MyList(data: List<PostItem>) {

    LazyColumn {
        itemsIndexed(items = data) { index, item ->
            ShowItem(data = item)
        }
    }
}

@Composable
fun ShowItem(data: PostItem, modifier: Modifier = Modifier) {

    var expand by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = MaterialTheme.shapes.medium
    ) {

        Row(
            modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            Column(modifier = modifier.weight(1f)) {
                Text(
                    text = "text 1",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "text 2",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (expand) {
                    Text(
                        text = data.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            FilledTonalButton(onClick = { expand = !expand }) {
                Text("Show More")
            }
        }
    }
}


@Preview(
//    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark1"
)
@Composable
fun OnBoardingScreenPreview1() {
    RetrofitMVVMTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val dummyData = PostItem(
                "Dummy body", 1, "Dummy title", 123
            )
            ShowItem(data = dummyData)
        }
    }
}