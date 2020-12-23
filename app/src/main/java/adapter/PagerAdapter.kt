package adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import fragment.TabFragment1


class PagerAdapter(fm: FragmentManager?, var mNumOfTabs: Int) :
    FragmentStatePagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                TabFragment1()
            }
            1 -> {
                TabFragment1()
            }
            2 -> {
                TabFragment1()
            }
            3 -> {
                TabFragment1()
            }
//            else -> null
           // else -> null
            else -> TabFragment1()
        }
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }

}