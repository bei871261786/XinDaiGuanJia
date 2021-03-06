
import android.app.Activity
import android.view.View
import android.widget.EditText
import hxz.www.commonbase.util.DisplayUtil
import hxz.www.commonbase.util.RegexUtil
import hxz.www.commonbase.util.SoftKeyBoardListener
import kotlin.math.abs


/**
 * 如果需要跟随键盘，需要跟随的模块（view）调用此方法即可
 *  moduleHeight : 模块底部距离顶部高度
 */
fun View?.followKeybord(activity: Activity, moduleHeight: Int) {
    var view = this
    var scrollvalue = 0f
    SoftKeyBoardListener(activity, object : SoftKeyBoardListener.OnSoftKeyBoardChangeListener {
        override fun keyBoardShow(height: Int) {
            var ramdingHeight = abs(DisplayUtil.getScreenHeight() - height)
            scrollvalue = if (ramdingHeight >= moduleHeight) 0F else (ramdingHeight - moduleHeight) * 1F
            view?.animate()?.translationY(scrollvalue)?.setDuration(100)?.start()
        }

        override fun keyBoardHide(height: Int) {
            view?.animate()?.translationY(0f)?.setDuration(300)?.start()
        }
    })
}



/**
 * toast服务器返回的错误提示，统一处理
 */
fun String?.toastNetError() {
    this.value("").substringAfter(":").toast()
}


/**
 * 体重显示转换
 */
fun Int.weight2Str() = when {
    this < 40 -> "<" + 40 + "kg"
    this > 100 -> ">" + 100 + "kg"
    else -> "" + this + "kg"
}

/**
 * 身高显示转换
 */
fun Int.height2Str() = when {
    this < 150 -> "<" + 150 + "cm"
    this > 200 -> ">" + 200 + "cm"
    else -> "" + this + "cm"
}

fun EditText.checkPass() = if (RegexUtil.checkPassword(this.getTextStr().value())) true else {
    "密码格式错误，请重新填写".toast()
    false
}
