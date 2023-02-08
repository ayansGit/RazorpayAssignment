package com.ayan.razorpayassignment.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ayan.razorpayassignment.*
import com.ayan.razorpayassignment.databinding.ActivityMainBinding
import com.ayan.razorpayassignment.models.DisplayData
import com.ayan.razorpayassignment.models.UiResponse
import com.ayan.rnetwork.RNetwork
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var inputs: ArrayList<EditText>
    private lateinit var labels: ArrayList<String>
    private lateinit var displayList: ArrayList<DisplayData>

    companion object{
        val EXTRA_DISPLAY_DATA = "display.data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        getUI()
    }

    private fun getUI(){

        lifecycleScope.launch {

            binding.loader.show()
            binding.parent.removeAllViews()
            val ui = callUIData()
            Log.d(TAG, "UI: $ui")

            var gson = Gson()
            var uiResp = gson.fromJson(ui.toString(), UiResponse::class.java)
            Log.d(TAG, "UI: ${uiResp}")

            uiResp?.let {
                val logo = getLogo(uiResp.logoUrl)
                logo?.let { binding.parent.addView(getLogoImage(it)) }
                binding.parent.addView(getHeading(uiResp.headingText))
            }

            inputs = ArrayList()
            labels = ArrayList()

           uiResp?.uidata?.forEach {

               when (it.uitype) {

                   "label" -> {
                       binding.parent.addView(getLabel(it.value, it.key))
                       labels.add(it.value)
                   }

                   "edittext" -> {
                       val editText = getEditText(it.hint, it.key)
                       binding.parent.addView(editText)
                       inputs.add(editText)
                   }

                   "button" -> {
                       val button = getButton(it.value)
                       binding.parent.addView(button)
                       button.setOnClickListener {
                           sendDisplayData()
                       }
                   }
               }
           }

           binding.loader.hide()

        }

    }

    private fun sendDisplayData(){

       displayList = ArrayList()
       for(index in inputs.indices){
           val label = if(labels.size>index) labels[index] else null
           val value = inputs[index].text.toString()
           val displayData = DisplayData(
               label,
               value
           )
           displayList.add(displayData)
       }
       val intent = Intent(this, DisplayActivity::class.java)
       intent.putParcelableArrayListExtra(EXTRA_DISPLAY_DATA, displayList)
       startActivity(intent)

    }


    private suspend fun callUIData(): JSONObject?{

        var response: JSONObject

        withContext(Dispatchers.IO){
            response = RNetwork.getInstance().fetchCustomUI("https://demo.ezetap.com/mobileapps/android_assignment.json")
        }
        return response;
    }

    private suspend fun getLogo(url: String): ByteArray?{
        var logo : ByteArray
        withContext(Dispatchers.IO){
            logo = RNetwork.getInstance().fetchLogo(url)
        }
        return logo;
    }
}