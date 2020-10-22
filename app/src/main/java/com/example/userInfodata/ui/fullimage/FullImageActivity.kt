package com.example.userInfodata.ui.fullimage

import android.os.Bundle
import com.example.userInfodata.R
import com.example.userInfodata.di.component.ActivityComponent
import com.example.userInfodata.ui.base.BaseActivity
import com.example.userInfodata.utils.display.PicassoSSL
import kotlinx.android.synthetic.main.activity_full_image.*

class FullImageActivity : BaseActivity<FullImageViewModel>() {
    override fun provideLayoutId(): Int = R.layout.activity_full_image

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        toolbarAlbumId.setText("Album Id: " + intent.getIntExtra("albumId", 0))
        toolbarPhotoId.setText("Photo Id: " + intent.getIntExtra("photoId", 0))
        itemFullTitle.setText(intent.getStringExtra("title"))
        PicassoSSL.getInstance(this)?.load(intent.getStringExtra("url"))
            ?.placeholder(null)
            ?.into(itemFullIV)
    }

}