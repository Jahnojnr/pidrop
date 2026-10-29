package com.pidrop.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OnboardingNavigationTest {
    @get:Rule
    val compose = createAndroidComposeRule<MainActivity>()

    private fun text(id: Int) = compose.activity.getString(id)

    private fun systemBack() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
    }

    @Test
    fun customerAndRiderLeadToDistinctPlaceholdersAndSystemBackReturns() {
        compose.onNodeWithText(text(R.string.get_started)).performClick()
        compose.onNodeWithText(text(R.string.role_title)).assertIsDisplayed()
        compose.onNodeWithText(text(R.string.role_customer)).performClick()
        compose.onNodeWithText(text(R.string.customer_placeholder_title)).assertIsDisplayed()
        compose.onNodeWithText(text(R.string.customer_placeholder_description)).assertIsDisplayed()

        systemBack()
        compose.onNodeWithText(text(R.string.role_title)).assertIsDisplayed()
        compose.onNodeWithText(text(R.string.role_rider)).performClick()
        compose.onNodeWithText(text(R.string.rider_placeholder_title)).assertIsDisplayed()
        compose.onNodeWithText(text(R.string.rider_placeholder_description)).assertIsDisplayed()

        systemBack()
        compose.onNodeWithText(text(R.string.role_title)).assertIsDisplayed()
        systemBack()
        compose.onNodeWithText(text(R.string.get_started)).assertIsDisplayed()
    }

    @Test
    fun visibleBackAndExistingAboutNavigationWork() {
        compose.onNodeWithText(text(R.string.get_started)).performClick()
        compose.onNodeWithText(text(R.string.role_rider)).performClick()
        compose.onNodeWithText(text(R.string.back)).performClick()
        compose.onNodeWithText(text(R.string.role_title)).assertIsDisplayed()
        compose.onNodeWithText(text(R.string.back)).performClick()
        compose.onNodeWithText(text(R.string.welcome_action)).performClick()
        compose.onNodeWithText(text(R.string.about_title)).assertIsDisplayed()
        compose.onNodeWithText(text(R.string.back)).performClick()
        compose.onNodeWithText(text(R.string.get_started)).assertIsDisplayed()
    }
}
