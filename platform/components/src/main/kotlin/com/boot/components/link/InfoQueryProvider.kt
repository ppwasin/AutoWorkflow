package com.boot.components.link

interface InfoQueryProvider {
  fun getLinkQueryParams(): Map<String, String>
}

