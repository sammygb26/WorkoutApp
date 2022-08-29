package com.example.workoutapp.data

import android.content.Context
import android.util.Xml
import com.example.workoutapp.model.workout.Workout
import com.example.workoutapp.model.workout.WorkoutSection
import com.example.workoutapp.model.workout.WorkoutSectionType
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlSerializer
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.lang.IllegalStateException
import java.util.*

class WorkoutFileSystemManager(val context: Context) {

    val workoutNames = findWorkouts()

    fun writeWorkout(workout: Workout) {
        val fileName = nameToFileName(workout.name)
        val fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)

        val serializer: XmlSerializer = Xml.newSerializer()
        serializer.setOutput(fos, "UTF-8")
        serializer.startTag(null, "workout")

        serializerWriteField(serializer, "description", workout.description)
        serializer.startTag(null, "sections")

        for (section in workout.sections) {
            serializer.startTag(null, "section")

            serializerWriteField(serializer, "name", section.name)
            serializerWriteField(serializer, "type", section.type.toString())
            serializerWriteField(serializer, "number", section.number.toString())
            serializerWriteField(serializer, "numberFormatString", section.numberFormatString)
            serializerWriteField(serializer, "description", section.description)

            serializer.endTag(null, "section")
        }

        serializer.endTag(null, "sections")
        serializer.endTag(null, "workout")

        serializer.endDocument()
        serializer.flush()

        fos.close()
    }

    fun readWorkout(name: String) : Workout?{
        if (!workoutNames.contains(name))
            return null

        val fileName = nameToFileName(name)
        val fis = FileInputStream(File(context.filesDir, fileName))
        val parser: XmlPullParser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(fis, null)
        parser.nextTag()
        return readWorkout(name, parser)
    }

    private fun nameToFileName(name : String) = "workout_$name.xml"

    private fun fileNameToName(fileName: String) : String? {
        if (fileName.startsWith("workout_") && fileName.endsWith(".xml")) {
            return fileName.removePrefix("workout_").removeSuffix(".xml")
        }
        return null
    }

    private fun findWorkouts() : List<String>{
        var workoutFiles = context.filesDir.list()!!.map { it!! }
        workoutFiles = workoutFiles.filter { it.startsWith("workout_") && it.endsWith(".xml") }
        return workoutFiles.map { fileNameToName(it)!! }
    }

    private fun readWorkout(name: String, parser: XmlPullParser) : Workout {
        val workout = Workout(name)

        parser.require(XmlPullParser.START_TAG, null, "workout")
        while(parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG)
                continue

            when (parser.name) {
                "description" -> workout.description = readDescription(parser)
                "sections" -> workout.sections = readSections(parser)
                else -> skip(parser)
            }
        }

        return workout
    }

    private fun readName(parser: XmlPullParser) : String {
        parser.require(XmlPullParser.START_TAG, null, "name")
        val description = readText(parser)
        parser.require(XmlPullParser.END_TAG, null, "name")
        return description
    }

    private fun readDescription(parser: XmlPullParser) : String {
        parser.require(XmlPullParser.START_TAG, null, "description")
        val description = readText(parser)
        parser.require(XmlPullParser.END_TAG, null, "description")
        return description
    }

    private fun readSections(parser: XmlPullParser): List<WorkoutSection> {
        val sections : MutableList<WorkoutSection> = mutableListOf()
        parser.require(XmlPullParser.START_TAG, null, "sections")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG)
                continue

            when(parser.name) {
                "section" -> sections.add(readSection(parser))
                else -> skip(parser)
            }
        }

        return sections
    }

    private fun readSection(parser: XmlPullParser) : WorkoutSection {
        val section = WorkoutSection()

        parser.require(XmlPullParser.START_TAG, null, "section")

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG)
                continue
            when(parser.name) {
                "name" -> section.name = readName(parser)
                "type" -> section.type = readType(parser)
                "number" -> section.number = readNumber(parser)
                "numberFormatString" -> section.numberFormatString = readNumberFormatString(parser)
                "description" -> section.description = readDescription(parser)
                else -> skip(parser)
            }
        }

        return section
    }

    private fun readType(parser: XmlPullParser) : WorkoutSectionType {
        parser.require(XmlPullParser.START_TAG, null, "type")
        val text = readText(parser)
        parser.require(XmlPullParser.END_TAG, null, "type")
        return when (text) {
            WorkoutSectionType.CHECK.toString() -> WorkoutSectionType.CHECK
            WorkoutSectionType.TIMER.toString() -> WorkoutSectionType.TIMER
            else -> WorkoutSectionType.TIMER
        }
    }

    private fun readNumber(parser: XmlPullParser) : Int {
        parser.require(XmlPullParser.START_TAG, null, "number")
        val text = readText(parser)
        parser.require(XmlPullParser.END_TAG, null, "number")
        return text.toInt()
    }

    private fun readNumberFormatString(parser: XmlPullParser) : String {
        parser.require(XmlPullParser.START_TAG, null, "numberFormatString")
        val numberFOrmatString = readText(parser)
        parser.require(XmlPullParser.END_TAG, null, "numberFormatString")
        return numberFOrmatString
    }

    private fun readText(parser: XmlPullParser): String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun skip(parser: XmlPullParser) {
        if (parser.eventType != XmlPullParser.START_TAG)
            throw IllegalStateException()

        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.START_TAG -> depth++
                XmlPullParser.END_TAG -> depth--
            }
        }
    }



    private fun serializerWriteField(serializer: XmlSerializer , fieldName: String, text: String) {
        serializer.startTag(null, fieldName)
        serializer.text(text)
        serializer.endTag(null, fieldName)
    }
}