package com.example.qrcodereader.ui.composable

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.qrcodereader.MyQrCodeActivity
import com.example.qrcodereader.R

@Composable
fun QrCodeScannerButton(onResult: (String) -> Unit) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            val data = result.data?.getStringExtra("SCAN_RESULT")
            Log.d("SCAN_RESULT",data.toString())
            onResult(data?:"")
        }
    }
    Image(painter = painterResource(id = R.drawable.ic_qrcode), contentDescription =null,
        modifier = Modifier.size(350.dp).padding(top = 150.dp).clickable {
            val intent = Intent (context, MyQrCodeActivity::class.java)
            launcher.launch(intent)
        })
}