# AnimationSystem
Java port of my Kotlin <a href = "https://github.com/Wolfsurge/AnimationSystem">Animation System</a>.

## Add to projects
### Gradle

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Wolfsurge:JavaAnimationSystem:1.1'
}
```

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
  
<dependencies>
    <dependency>
        <groupId>com.github.Wolfsurge</groupId>
        <artifactId>JavaAnimationSystem</artifactId>
        <version>1.1</version>
    </dependency>
</dependencies>
```

## Usage
Create an animation object by instantiating the Animation class:

```java
Animation animation = new Animation(() -> 200f, false, () -> Easing.LINEAR);
```

The constructor takes in three parameters:<br>
`length` A supplier that provides the length (duration) of the animation.<br>
`initialState` A boolean that determines whether the animation should start expanded or closed.<br>
`easing` A supplier that provides the easing we want to use for the animation.

You can set the state of the animation at any time, by simply setting the `state` variable.<br>
```java
animation.setState(true);
```

You can get the current value of the animation by calling `getAnimationFactor()`<br>
This method returns a double between 0 and 1, representing the current progress of the animation.

When applying this, it should be used in a similar way to this:
```java
double y = 100.0 * animation.getAnimationFactor();
```

The `resetToDefault` method instantly resets the animation to its original state.

Need more help? Check out the src/test directory!
