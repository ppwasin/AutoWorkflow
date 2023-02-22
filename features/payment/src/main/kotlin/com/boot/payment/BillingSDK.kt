@file:Suppress("UNUSED_VARIABLE")

package com.boot.payment

import android.app.Activity
import android.app.Application
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClient.BillingResponseCode.BILLING_UNAVAILABLE
import com.android.billingclient.api.BillingClient.BillingResponseCode.DEVELOPER_ERROR
import com.android.billingclient.api.BillingClient.BillingResponseCode.ERROR
import com.android.billingclient.api.BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED
import com.android.billingclient.api.BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED
import com.android.billingclient.api.BillingClient.BillingResponseCode.ITEM_NOT_OWNED
import com.android.billingclient.api.BillingClient.BillingResponseCode.ITEM_UNAVAILABLE
import com.android.billingclient.api.BillingClient.BillingResponseCode.OK
import com.android.billingclient.api.BillingClient.BillingResponseCode.SERVICE_DISCONNECTED
import com.android.billingclient.api.BillingClient.BillingResponseCode.SERVICE_TIMEOUT
import com.android.billingclient.api.BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE
import com.android.billingclient.api.BillingClient.BillingResponseCode.USER_CANCELED
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.queryProductDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
TODO need to try
- change application to Activity context
*/
class BillingSdk(private val application: Application) {
  // Estiblish BillingClient, Must be singleton
  private val purchasesUpdatedListener =
    PurchasesUpdatedListener { billingResult, purchases ->
      // To be implemented in a later section.
      billingResult.responseCode
    }

  private var billingClient =
    BillingClient.newBuilder(application)
      .setListener(purchasesUpdatedListener)
      .enablePendingPurchases()
      .build()

  // Connect to GooglePlay
  fun connect() {
    billingClient.startConnection(
      object : BillingClientStateListener {
        override fun onBillingSetupFinished(billingResult: BillingResult) {
          if (billingResult.responseCode == OK) {
            // The BillingClient is ready. You can query purchases here.
          }
        }

        override fun onBillingServiceDisconnected() {
          // Try to restart the connection on the next request to
          // Google Play by calling the startConnection() method.
        }
      },
    )
  }

  // Show products available to buy
  suspend fun getProducts() {
    val productList =
      listOf(
        QueryProductDetailsParams.Product.newBuilder()
          .setProductId("product_id_example")
          .setProductType(BillingClient.ProductType.SUBS)
          .build(),
      )
    val params =
      QueryProductDetailsParams.newBuilder().setProductList(productList)

    // leverage queryProductDetails Kotlin extension function
    val productDetailsResult =
      withContext(Dispatchers.IO) {
        billingClient.queryProductDetails(params.build())
      }

    // - View Product information in ProductDetailsClass
    // - Before offering a subscription, verify that the user is not already subscribed.
    // - Use isFeatureSupported() to check that device has google play version which support
    // subscription
    productDetailsResult.productDetailsList
  }

  fun launchPurchaseFlow(activity: Activity, productDetails: ProductDetails) {
    val selectedOfferToken =
      productDetails.subscriptionOfferDetails // EIther Trail or Normal
    val productDetailsParamsList =
      listOf(
        BillingFlowParams.ProductDetailsParams.newBuilder()
          // retrieve a value for "productDetails" by calling queryProductDetailsAsync()
          .setProductDetails(productDetails)
          // to get an offer token, call ProductDetails.subscriptionOfferDetails()
          // for a list of offers that are available to the user
          //        .setOfferToken(selectedOfferToken)
          .build(),
      )

    val billingFlowParams =
      BillingFlowParams.newBuilder()
        .setProductDetailsParamsList(productDetailsParamsList)
        .build()

    // Launch the billing flow
    val billingResult =
      billingClient.launchBillingFlow(activity, billingFlowParams)
  }
}

// https://developer.android.com/reference/com/android/billingclient/api/BillingClient.BillingResponseCode#service_disconnected
enum class BillingResultCode {
  BILLING_UNAVAILABLE,
  DEVELOPER_ERROR,
  ERROR,
  FEATURE_NOT_SUPPORTED,
  ITEM_ALREADY_OWNED,
  ITEM_NOT_OWNED,
  ITEM_UNAVAILABLE,
  OK,
  SERVICE_DISCONNECTED,
  SERVICE_TIMEOUT,
  SERVICE_UNAVAILABLE,
  USER_CANCELED
}

fun BillingResult.toCode(): BillingResultCode? {
  return when (responseCode) {
    SERVICE_TIMEOUT -> BillingResultCode.SERVICE_TIMEOUT
    FEATURE_NOT_SUPPORTED -> BillingResultCode.FEATURE_NOT_SUPPORTED
    SERVICE_DISCONNECTED -> BillingResultCode.SERVICE_DISCONNECTED
    SERVICE_UNAVAILABLE -> BillingResultCode.SERVICE_UNAVAILABLE
    BILLING_UNAVAILABLE -> BillingResultCode.BILLING_UNAVAILABLE
    ITEM_UNAVAILABLE -> BillingResultCode.ITEM_UNAVAILABLE
    DEVELOPER_ERROR -> BillingResultCode.DEVELOPER_ERROR
    ERROR -> BillingResultCode.ERROR
    ITEM_ALREADY_OWNED -> BillingResultCode.ITEM_ALREADY_OWNED
    ITEM_NOT_OWNED -> BillingResultCode.ITEM_NOT_OWNED
    USER_CANCELED -> BillingResultCode.USER_CANCELED
    OK -> BillingResultCode.OK
    else -> null
  }
}
