package com.abdykadyr.mathgame.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.abdykadyr.mathgame.R
import com.abdykadyr.mathgame.domain.entity.GameResult

//game finished adapters

@BindingAdapter("bindLoseOrWinImage")
fun bindLoseOrWinImage(imageView: ImageView, winner: Boolean) {
    if (winner) {
        imageView.setImageResource(R.drawable.ic_baseline_win_100)
    } else {
        imageView.setImageResource(R.drawable.ic_baseline_loose_100)
    }
}

@BindingAdapter("bindLoseOrWinTitle")
fun bindLoseOrWinTitle(textView: TextView, winner: Boolean) {
    if (winner) {
        textView.setText(R.string.win_title)
    } else {
        textView.setText(R.string.lose_title)
    }
}

@BindingAdapter("bindMinRightAnswersTitle")
fun bindMinRightAnswersTitle(textView: TextView, minCount: Int) {
    textView.text = String.format(textView.context.getString(R.string.right_answers_title), minCount)
}

@BindingAdapter("bindTotalRightAnswersTitle")
fun bindTotalRightAnswersTitle(textView: TextView, count: Int) {
    textView.text = String.format(textView.context.getString(R.string.total_answers_title), count)
}

@BindingAdapter("bindPercentTitle")
fun bindPercentTitle(textView: TextView, minPercent: Int) {

    textView.text = String.format(textView.context.getString(R.string.percent_title), minPercent)
}

@BindingAdapter("bindTotalPercentTitle")
fun bindTotalPercentTitle(textView: TextView, gameResult: GameResult) {

    textView.text = String.format(
        textView.context.getString(R.string.total_percent_title),
        getPercentOfRightAnswers(gameResult)
    )
}

//GameFragment adapters

@BindingAdapter("bindIntToString")
fun bindIntToString(textView: TextView, sum: Int) {
    textView.text = sum.toString()
}

@BindingAdapter("bindAnswersProgressColor")
fun bindAnswersProgressColor(textView: TextView, enoughAnswers: Boolean) {
    textView.setTextColor(getColorByState(enoughAnswers, textView.context))
}


@BindingAdapter("bindAnswersProgressText")
fun bindAnswersProgressText(textView: TextView, text: String) {
    textView.text = text
}

@BindingAdapter("bindProgressBarEnoughPercent")
fun bindProgressBarEnoughPercent(progressBar: ProgressBar, enoughPercent: Boolean) {
    val color = getColorByState(enoughPercent,progressBar.context)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getPercentOfRightAnswers(gameResult: GameResult): Int {
    with(gameResult) {
        return if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }
}

private fun getColorByState(goodState: Boolean, context: Context) : Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else { android.R.color.holo_red_light }
    return ContextCompat.getColor(context,colorResId)
}