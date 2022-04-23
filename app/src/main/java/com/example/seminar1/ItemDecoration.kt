package com.example.seminar1

<<<<<<< Updated upstream
<<<<<<< Updated upstream
class ItemDecoration {
}
=======
=======
>>>>>>> Stashed changes
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration (context: Context, private val spanCount : Int) : RecyclerView.ItemDecoration() {

    private val margin = dpToPx(context, 20)

    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        //FollowerRecyclerView
        if(spanCount == 1){
            outRect.bottom = margin
        }else{   //RepositoryRecyclerView
            outRect.bottom = margin
            if(position % spanCount == 0){
                outRect.left = margin
                outRect.right = margin/2
            }else{
                outRect.left = margin/2
                outRect.right = margin
            }
        }
    }

    private fun dpToPx(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                context.resources.displayMetrics
        ).toInt()
    }
}

<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======

>>>>>>> Stashed changes
