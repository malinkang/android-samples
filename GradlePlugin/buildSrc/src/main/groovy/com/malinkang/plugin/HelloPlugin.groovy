package com.malinkang.plugin

import com.android.build.gradle.AppExtension;
import org.gradle.api.Plugin
import org.gradle.api.Project

class HelloPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        print("Hello Plugin ${project.name}")
        def appExtension = project.extensions.findByType(AppExtension.class)
        appExtension.registerTransform(new MyTransform())
    }
}