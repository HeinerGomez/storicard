package com.avility.presentation.screens.take_photo_screen

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.avility.presentation.R
import com.avility.shared.core.extensions.createImageFile
import com.avility.shared.ui.Screen
import com.avility.shared.ui.components.containers.MainContainer
import com.avility.shared.ui.components.elements_forms.StoriButton
import com.avility.shared.ui.constants.MeasureSmallDimen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import timber.log.Timber
import java.io.FileOutputStream
import java.util.Objects

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun TakePhotoScreen(
    navController: NavController,
    viewModel: TakePhotoScreenViewModel = hiltViewModel()
) {
    val applicationContext = LocalContext.current
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    var isCameraPermissionGranted by remember { mutableStateOf(false) }
    var mustShowRationale by rememberSaveable { mutableStateOf(false) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        isCameraPermissionGranted = isGranted
    }

    val state = viewModel.uiState.value

    if(state.isPhotoSaved) {
        viewModel.dispatchAction(TakePhotoScreenAction.ClearState)
        Toast.makeText(applicationContext, R.string.photo_uploaded, Toast.LENGTH_LONG).show()
        navController.navigate(Screen.LoginScreen.route) {
            popUpTo(0)
        }
    }

    if (mustShowRationale && !isCameraPermissionGranted) {
        mustShowRationale = false
        viewModel.dispatchAction(TakePhotoScreenAction.ClearState)
        Toast.makeText(applicationContext, R.string.camera_permission_not_allowed, Toast.LENGTH_LONG).show()
        navController.navigate(Screen.LoginScreen.route) {
            popUpTo(0)
        }
    }

    state.msgErrorResource?.let {
        Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
    }

    MainContainer(
        isLoading = state.isLoading,
        header = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title_topbar_take_photo_data_screen),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        headerPadding = false
    ) {
        if (isCameraPermissionGranted) {
            mustShowRationale = false
            val controller = remember {
                LifecycleCameraController(applicationContext).apply {
                    setEnabledUseCases(
                        CameraController.IMAGE_CAPTURE
                    )
                    cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
                }
            }

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = stringResource(R.string.disclaimer_take_photo),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X03.value))
                }
                Column(
                    Modifier
                        .fillMaxSize()
                ) {
                    Box(Modifier.fillMaxSize()
                    ) {
                        CameraPreview(
                            controller = controller,
                            modifier = Modifier.fillMaxSize()
                        )

                        Row(modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(MeasureSmallDimen.DIMEN_X03.value)) {
                            StoriButton(
                                text = stringResource(R.string.btn_take_photo),
                                enabled = !state.isLoading,
                                onTap = {
                                    takePhoto(
                                        controller = controller,
                                        context = applicationContext,
                                        onPhotoTaken = {
                                            viewModel.dispatchAction(TakePhotoScreenAction.SavePhoto(it))
                                        }
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(cameraPermissionState) {
        isCameraPermissionGranted = cameraPermissionState.status.isGranted
        if (!cameraPermissionState.status.isGranted && cameraPermissionState.status.shouldShowRationale) {
            mustShowRationale = true
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}

@Composable
fun CameraPreview(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        factory = {
            PreviewView(it).apply {
                this.controller = controller
                controller.bindToLifecycle(lifecycleOwner)
            }
        },
        modifier = modifier
    )
}

private fun takePhoto(
    controller: LifecycleCameraController,
    context: Context,
    onPhotoTaken: (Uri) -> Unit
) {
    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)
                val file = context.createImageFile()

                image.toBitmap().compress(
                    Bitmap.CompressFormat.JPEG,
                    60,
                    FileOutputStream(
                        file
                    )
                )

                val uri = FileProvider.getUriForFile(
                    Objects.requireNonNull(context),
                    "com.avility.android_firebase" + ".provider",
                    file
                )

                onPhotoTaken(uri)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Timber.e(exception)
            }
        }
    )
}