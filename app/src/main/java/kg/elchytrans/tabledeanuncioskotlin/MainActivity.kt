package kg.elchytrans.tabledeanuncioskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kg.elchytrans.tabledeanuncioskotlin.databinding.ActivityMainBinding
import kg.elchytrans.tabledeanuncioskotlin.dialoghelper.DialogConst
import kg.elchytrans.tabledeanuncioskotlin.dialoghelper.DialogHelper
import kg.elchytrans.tabledeanuncioskotlin.dialoghelper.GoogleAccConst
import android.util.Log

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
        private lateinit var tvAccount:TextView
        private lateinit var rootElement:ActivityMainBinding
        private val dialogHelper=DialogHelper(this)
        val mAuth =FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement= ActivityMainBinding.inflate(layoutInflater)
         val view =rootElement.root
         setContentView(view)
        init()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==GoogleAccConst.GOOGLE_SIGN_IN_REQUEST_CODE){
            Log.d("MyLog", "Sign in result")
            //Toast.makeText(this, "Sign in result", Toast.LENGTH_SHORT).show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        tvAccount.text=resources.getString(R.string.not_reg)

    }

    private fun init() {
        var toggle = ActionBarDrawerToggle(this, rootElement.drawerLayout, rootElement.mainContent.toolbar, R.string.open, R.string.close)
        rootElement.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        rootElement.navView.setNavigationItemSelectedListener(this)
        tvAccount = rootElement.navView.getHeaderView(0).findViewById(R.id.tvAccountEmail)

    }

     override fun onNavigationItemSelected(item: MenuItem): Boolean {

         when(item.itemId) {
             R.id.id_my_ads -> {
              Toast.makeText(this, "Pressed id_my_ads", Toast.LENGTH_LONG).show()
             }
             R.id.id_car -> {
                 Toast.makeText(this, "Pressed id_car", Toast.LENGTH_LONG).show()


             }
             R.id.id_pc -> {
                 Toast.makeText(this, "Pressed id_pc", Toast.LENGTH_LONG).show()


             }
             R.id.id_smartphone -> {
                 Toast.makeText(this, "Pressed id_smart", Toast.LENGTH_LONG).show()


             }
             R.id.id_dm -> {
                 Toast.makeText(this, "Pressed id_dm", Toast.LENGTH_LONG).show()


             }
             R.id.id_sign_in -> {
                 dialogHelper.createSignDialog(DialogConst.SIGN_IN_STATE)


             }
             R.id.id_sign_up -> {
                 dialogHelper.createSignDialog(DialogConst.SIGN_UP_STATE)


             }
             R.id.id_sign_out -> {

                 uiUpdate(null)
                 mAuth.signOut()
             }

         }
         rootElement.drawerLayout.closeDrawer(GravityCompat.START)
         return true
     }

    fun uiUpdate(user: FirebaseUser?) {
        tvAccount.text = if(user == null){
            resources.getString(R.string.not_reg)
        } else {
            user.email
        }
        if (user==null) {
        Toast.makeText(this, "null", Toast.LENGTH_LONG).show() }
        else {
            Toast.makeText(this, "not null", Toast.LENGTH_LONG).show() }

    }

 }