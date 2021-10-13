package com.boot.components.link

import com.boot.components.link.dep.MemberService
import com.boot.components.link.dep.PriceProvider

class InfoQueryProviderImpl (
  private val memberService: MemberService,
  private val priceProvider: PriceProvider
  ): InfoQueryProvider {
  override fun getLinkQueryParams(): Map<String, String> {
    val queryParamMaps = mutableMapOf<String, String>()
    if(memberService.isLogin()) {
      val memberId = memberService.getMemberId()
      if(memberId != null)
      queryParamMaps["memberId"] = memberId.toString()
    }
    return queryParamMaps
  }
}