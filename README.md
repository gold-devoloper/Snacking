# Snacking
Styling your snackbar easily and quickly. This library can be used in both Java and Kotlin.

## Installation
#### repositories
```gradle
maven { url 'https://jitpack.io' }
```

#### dependencies
```gradle
implementation 'com.github.eriffanani:Snacking:1.1.0'
```

## How To Use
#### Xml Layout
```xml
<CoordinatorLayout
    android:id="@+id/parentView"/>
    <ChildView/>
    <ExtendedFloatingActionButton
        android:id="@+id/fab"
        android:layout_gravity="bottom|end"/>
</CoordinatorLayout>
```
#### Activity / Fragment
```kotlin
val parentView: CoordinatorLayout = findViewById(R.id.parentView)
val fab: ExtendedFloatingActionButton = findViewById(R.id.fab)
```
* Basic
```kotlin
Snacking.Builder(parentView, "Hello! this is basic message")
    .build().show()
```

* With Icon
```kotlin
.icon(R.drawable.ic_info, R.color.teal_200)
```

* With Action
```kotlin
.action("Dismiss", R.color.teal_200) { snackBar ->
    // TODO ACTION
}
```

* Use Margin
```kotlin
.useMargin(true)
```

* Corner Radius
```kotlin
.cornerRadius(R.dimen.snack_bar_corner_radius)
```

* Corner Radius (Custom)
```kotlin
.useMargin(true)
(Top Left, Top Right, Bottom Left, Bottom Right)
.cornerRadius(R.dimen.snack_bar_corner_radius, 0, 0, R.dimen.snack_bar_corner_radius)
```

* Border
```kotlin
.border(R.dimen.snack_bar_border_size, R.color.teal_700)
```

* Background Color
```kotlin
.backgroundColor(R.color.purple_200)
```

* Background Image
```kotlin
.background(R.mipmap.img_gradient)
```

* Text Color
```kotlin
.textColor("#ffca28") / R.color.colorGrey
```

* Font
```kotlin
.fontFamily(R.font.montserrat)
```

* Anchor View
```kotlin
.anchorView(fab)
```

* State
```kotlin
Snacking.State(parentView!!, Snacking.State.INFO)
    .message("This message is using state")
    .show()
```

* Top Position
```kotlin
.position(Snacking.TOP)
```

* Message max lines
```kotlin
.messageMaxLines(2)
```

* Support for landscape
```kotlin
.landscapeStyle(Snacking.CENTER)
```

### Licence
```license
Copyright 2022 Mukhammad Erif Fanani

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
