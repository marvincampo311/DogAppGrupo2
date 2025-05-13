package com.example.dogapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val huellaAnim = findViewById<LottieAnimationView>(R.id.huellaAnim)
            huellaAnim.setOnClickListener {


                huellaAnim.setOnClickListener {
                    showBiometricPrompt()
                }

            }




        val biometricManager = androidx.biometric.BiometricManager.from(this)


        when(biometricManager.canAuthenticate(BIOMETRIC_STRONG )){
            androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS -> {
                showToast("presiona la huella para autenticar")

            }

            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->{
                showToast("Hardware biometrico no disponible")

            }

            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                showToast("harware biometrico no ddisponible")

            }

            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                showToast("no hay biometria registrada")

            }

        }


    }

    private fun showBiometricPrompt(){
        val executor = ContextCompat.getMainExecutor(this)

        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    showToast("autenticacion exitosa")
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    showToast("ERROR $errString")
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    showToast("autenticacion fallida")
                }

            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("AUTENTICACION BIOMETRICA")
            .setSubtitle("USA TU HUELLA DIJITAL PARA CONTINUAR")
            .setNegativeButtonText("CANCELAR")
            .build()


        biometricPrompt.authenticate(promptInfo)



    }

    private fun showToast(message: String){
        Toast.makeText(this, message , Toast.LENGTH_LONG).show()
        }
    }



