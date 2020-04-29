/**
 * Adapter pattern put together incompatible interfaces by providing common interfaces between them.
 * It's necessary to convert one interface to another by using special object called - Adapter.
 * At the end we'll have convenient interface.s
 * */
class UnknownAudioTypeException : Exception {
    constructor() : super()
    constructor(message: String = "Unknown media type.") : super(message)
}

enum class AudioType {
    MP3, WAV
}

interface MediaPlayer {
    fun play(audioType: AudioType, filename: String): Unit
}

interface AdvancedMediaPlayer {
    fun playMP3(filename: String): Unit
    fun playWAV(filename: String): Unit
}

class MP3Player : AdvancedMediaPlayer {
    override fun playMP3(filename: String): Unit = println("Playing MP3 file called: $filename")

    override fun playWAV(filename: String): Unit = throw UnknownAudioTypeException()
}

class WAVPlayer : AdvancedMediaPlayer {
    override fun playMP3(filename: String) = throw UnknownAudioTypeException()

    override fun playWAV(filename: String) = println("Playing WAV file called: $filename")
}

class MediaAdapter : MediaPlayer {
    override fun play(audioType: AudioType, filename: String): Unit = when (audioType) {
        AudioType.MP3 -> MP3Player().playMP3(filename)
        AudioType.WAV -> WAVPlayer().playWAV(filename)
    }
}

class AudioPlayer : MediaPlayer {
    override fun play(audioType: AudioType, filename: String): Unit = when (audioType) {
        AudioType.MP3 -> MediaAdapter().play(audioType, filename)
        AudioType.WAV -> MediaAdapter().play(audioType, filename)
    }
}

class Test {
    @Test
    fun test() {
        val player: AudioPlayer = AudioPlayer()

        player.play(AudioType.MP3, "mr.sandman.mp3")
        player.play(AudioType.WAV, "love me tender.wav")
        player.play(AudioType.MP3, "fly me to the moon.mp3")
    }
}