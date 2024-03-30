package com.avility.presentation.screens.take_photo_screen

import android.net.Uri

sealed class TakePhotoScreenAction {
    class SavePhoto(val photo: Uri) : TakePhotoScreenAction()

    object ClearState : TakePhotoScreenAction()
}
