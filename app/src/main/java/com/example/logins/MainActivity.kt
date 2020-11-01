package com.example.logins

import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.facebook.share.internal.MessengerShareContentUtility.URL
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import java.util.*


@Throws(MalformedURLException::class)
private fun recuperaFotoPerfilFacebook(userID: String): Uri.Builder {
    val builder: Uri.Builder = Uri.parse("https://graph.facebook.com").buildUpon()
    builder.appendPath(userID).appendPath("picture").appendQueryParameter("type", "large")
    return builder
}

class MainActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null

    private var loginButton : LoginButton? = null

    private var img: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginButton?.setReadPermissions(Arrays.asList("email", "public_picture"))
        loginButton = findViewById(R.id.login_button)

        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult?> {
                    override fun onSuccess(loginResult: LoginResult?) {
                        // App code
                        if (loginResult != null) {
//                            var imgPerfil: Uri.Builder = recuperaFotoPerfilFacebook(loginResult.accessToken.userId)
//                            var teste = BitmapFactory.decodeByteArray(imgPerfil.toString().toByteArray(), 0, imgPerfil.toString().toByteArray().size)
                            img = findViewById(R.id.img1)
//                            img?.setImageBitmap(teste)
//                            val profileImg = "https://graph.facebook.com/" + loginResult.accessToken.userId.toString() + "/picture?type=large&width=1080"
//                            var teste2 = BitmapFactory.decodeByteArray(profileImg.toByteArray(),0, profileImg.toByteArray().size)
//                            img?.setImageBitmap(teste2)
                            val width = 0.5
                            val height = 0.5
                            var imageURL = URL("https://graph.facebook.com/" + loginResult.accessToken.userId.toString() + "/picture?width=" + width.toString() + "&height=" + height)
                            val inputStream: InputStream = imageURL.getContent() as InputStream
                            var bitmap = BitmapFactory.decodeStream(inputStream)
                            img?.setImageBitmap(bitmap)

                        }

                    }

                    override fun onCancel() {
                        // App code
                    }

                    override fun onError(exception: FacebookException) {
                        // App code
                    }
                })
    }

//    @Override
//    protected void onActivityResult(int resquestCode, int resultCode, @Nullable Intent data){
//        callbackManager.onActivityResult((requestCode, resultCode, data))
//        supre.OnActivityResult(requestCode, resultCode, data)
//    }

}