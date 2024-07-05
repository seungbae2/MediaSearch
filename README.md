## 이미지 검색 앱
<p align="center">
  <img src="screenshot/Screenshot_SearchScreen.png" alt="Search Screen" width="200" />
  <img src="screenshot/Screenshot_BookmarkScreen.png" alt="Bookmark Screen" width="200" />
  <img src="screenshot/Screenshot_SearchScreen_Search.png" alt="Search Screen Search" width="200" />
  <img src="screenshot/Screenshot_SearchScreen_Bookmark.png" alt="Search Screen Bookmark" width="200" />
</p>

## Guide
local.properties 파일에 카카오 rest api key 넣어 주세요
API_KEY="YOUR_KAKAO_REST_API_KEY"

## Language
* Kotlin

## Libraries
* AndroidX
* Kotlin Libraries (Coroutine, DataTime, Serialization)
* Compose
  * Material3
  * Navigation
* Paging3
* Retrofit
* Sandwich
* room
* Hilt
* Coil

## Gradle Dependency
Gradle Version Catalog

## Architecture
* Layered Architecture 
  * Data
  * Domain
  * Presentation

## Module
Multi-module 구조 
* Core 모듈 
  * common
  * designsystem
  * data
  * database
  * network
  * model
  * domain
* Feature 모듈
  * search
  * bookmark