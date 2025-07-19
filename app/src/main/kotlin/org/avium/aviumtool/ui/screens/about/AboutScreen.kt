package org.avium.aviumtool.ui.screens.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import org.avium.aviumtool.R
import org.avium.aviumtool.ui.components.SettingsCard
import org.avium.aviumtool.ui.components.TitledCard
import org.avium.aviumtool.utils.SystemUtils

@Composable
fun AboutScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        // 头图
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.small
            ) {
                Image(
                    painter = painterResource(id = R.drawable.about_page),
                    contentDescription = stringResource(id = R.string.about_header_image_desc),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // 设备信息
        item {
            DeviceInfoSection()
        }

        // 开发者
        item {
            ContributorsSection()
        }

        // 其他项目使用
        item {
            ProjectsSection()
        }
    }
}

@Composable
private fun DeviceInfoSection() {
    val model = remember { SystemUtils.getProp("ro.product.model") }
    val flymeVersion = remember { SystemUtils.getProp("ro.build.display.id") }
    val androidVersion = remember { SystemUtils.getProp("ro.build.version.release") }
    val socModel = remember { SystemUtils.getProp("ro.soc.model") }

    TitledCard(title = stringResource(id = R.string.about_section_device_info)) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            maxItemsInEachRow = 2
        ) {
            val itemModifier = Modifier.weight(1f)
            InfoItem(
                label = stringResource(R.string.about_info_model),
                value = model,
                modifier = itemModifier
            )
            InfoItem(
                label = stringResource(R.string.about_info_flyme_version),
                value = flymeVersion,
                modifier = itemModifier
            )


            InfoItem(
                label = stringResource(R.string.about_info_soc_info),
                value = socModel,
                modifier = itemModifier
            )
            InfoItem(
                label = stringResource(R.string.about_info_android_version),
                value = androidVersion,
                modifier = itemModifier
            )
        }
    }
}

@Composable
private fun InfoItem(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 12.sp,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}


@Composable
private fun ContributorsSection() {
    val context = LocalContext.current
    val openUrl = { url: String ->
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    TitledCard(title = stringResource(id = R.string.about_section_contributors)) {
        Column {
            ContributorRow(
                avatarRes = R.drawable.avatar_1,
                name = stringResource(R.string.contributor_name_1),
                onClick = { openUrl("https://github.com/Ruyue-Kinsenka") }
            )
//            Divider(modifier = Modifier.padding(horizontal = 16.dp), color = MaterialTheme.colorScheme.background)
            ContributorRow(
                avatarRes = R.drawable.avatar_2,
                name = stringResource(R.string.contributor_name_2),
                onClick = { openUrl("https://github.com/sorrow404Null") }
            )
//            Divider(modifier = Modifier.padding(horizontal = 16.dp), color = MaterialTheme.colorScheme.background)
            ContributorRow(
                avatarRes = R.drawable.avatar_3,
                name = stringResource(R.string.contributor_name_3),
                onClick = { openUrl("https://github.com/luluzzy") }
            )
        }
    }
}

@Composable
private fun ContributorRow(avatarRes: Int, name: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = avatarRes),
            contentDescription = "$name avatar",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = name, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun ProjectsSection() {
    val context = LocalContext.current
    val openUrl = { url: String ->
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    TitledCard(title = stringResource(id = R.string.about_section_open_source)) {
        Column {
            ProjectRow(
                name = stringResource(R.string.open_source_project_1),
                onClick = { openUrl("https://github.com/HighCapable/YukiHookAPI") }
            )
//            Divider(modifier = Modifier.padding(horizontal = 16.dp), color = MaterialTheme.colorScheme.background)
            ProjectRow(
                name = stringResource(R.string.open_source_project_2),
                onClick = { openUrl("https://developer.android.com/jetpack/compose") }
            )
        }
    }
}

@Composable
private fun ProjectRow(name: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
        )
    }
}