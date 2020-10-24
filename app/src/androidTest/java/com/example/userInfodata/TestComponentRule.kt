package com.example.userInfodata

import android.content.Context
import com.example.userInfodata.di.component.DaggerTestComponent
import com.example.userInfodata.di.component.TestComponent
import com.example.userInfodata.di.module.ApplicationTestModule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestComponentRule(private val context: Context) : TestRule {

    private var testComponent: TestComponent? = null

    fun getContext() = context

    private fun setupDaggerTestComponentInApplication() {
        val application = context.applicationContext as UserInfoApplication
        testComponent = DaggerTestComponent.builder()
            .applicationTestModule(ApplicationTestModule(application))
            .build()
        application.setComponent(testComponent!!)
    }

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                try {
                    setupDaggerTestComponentInApplication()
                    base.evaluate()
                } finally {
                    testComponent = null
                }
            }
        }
    }

}