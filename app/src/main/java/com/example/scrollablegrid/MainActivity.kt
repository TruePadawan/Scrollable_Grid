package com.example.scrollablegrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scrollablegrid.data.DataSource
import com.example.scrollablegrid.model.Topic
import com.example.scrollablegrid.ui.theme.ScrollableGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScrollableGridTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CourseTopicsGrid(
                        topics = DataSource.topics,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TopicGridItem(topic: Topic, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(topic.imageResourceId),
            contentDescription = stringResource(topic.titleResourceId),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(68.dp)
                .aspectRatio(1f)
        )

        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .weight(1f)
        ) {
            Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)) {
                Text(
                    text = LocalContext.current.getString(topic.titleResourceId),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            Color.DarkGray
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = topic.courseCount.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Composable
fun CourseTopicsGrid(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(items = topics, key = { it.titleResourceId }) { topic ->
            TopicGridItem(topic, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
fun CourseTopicsGridPreview() {
    CourseTopicsGrid(topics = DataSource.topics)
}

@Preview(showBackground = true)
@Composable
fun TopicGridItemPreview() {
    TopicGridItem(
        topic = Topic(
            titleResourceId = R.string.photography,
            courseCount = 321,
            imageResourceId = R.drawable.photography
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ScrollableGridPreview() {
    ScrollableGridTheme {

    }
}