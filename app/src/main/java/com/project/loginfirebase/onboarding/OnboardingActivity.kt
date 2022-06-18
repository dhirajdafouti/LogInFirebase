package com.project.loginfirebase.onboarding

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.project.loginfirebase.R
import com.project.loginfirebase.presentation.MainActivity

class OnboardingActivity : AppCompatActivity() {

    private lateinit var linearIndicator: LinearLayout
    private lateinit var customAdapter: CustomAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var buttonNext: Button
    private lateinit var buttonSkip: Button
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_onboarding)
        val onBoardingItem: List<OnBoardingItem> = onBoardingItemInitialization()
        viewPager2 = findViewById(R.id.onboardingViewPager)
        customAdapter = CustomAdapter(onBoardingItem)
        viewPager2.adapter = customAdapter
        setupOnBoardingIndicators()
        setCurrentIndicator(0)
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        buttonNext.setOnClickListener {
            if (viewPager2.currentItem + 1 < customAdapter.itemCount) {
                viewPager2.currentItem += 1
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        buttonSkip.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = linearIndicator.childCount
        for (i in 0 until childCount) {
            val imageView = linearIndicator[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                    applicationContext, R.drawable.onboarding_inactive
                ))
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                    applicationContext, R.drawable.onboarding_active
                ))
            }

        }

    }


    private fun setupOnBoardingIndicators() {
        val imageView: Array<ImageView?> = arrayOfNulls<ImageView>(customAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.leftMargin = 8
        layoutParams.rightMargin = 8
        layoutParams.topMargin = 0
        layoutParams.bottomMargin = 0

        for (i in imageView.indices) {
            imageView[i] = ImageView(applicationContext)
            imageView[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.onboarding_inactive)
                )
                this?.layoutParams = layoutParams
            }
            linearIndicator.addView(imageView[i])
        }


    }

    private fun onBoardingItemInitialization(): List<OnBoardingItem> {
        val onBoardingItem: List<OnBoardingItem> = listOf(
            OnBoardingItem(R.drawable.unsplash_1, "Start You Journey", "The SYNAOS Journey Begins"),
            OnBoardingItem(R.drawable.unsplash_2,
                "Journey Android App Developer",
                "Features Application WareHouse Design"),
            OnBoardingItem(R.drawable.unsplash_3,
                "Journey Of Team Lead",
                "The Team Lead Journey Started..Happy After")

        )

        return onBoardingItem
    }

}