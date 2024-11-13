# 2024년 AIML 응용프로젝트 server 리포지토리

# 일반 API 명세

[//]: # (![]&#40;https://img.shields.io/static/v1?label=&message=GET&color=blue&#41;)

[//]: # (![]&#40;https://img.shields.io/static/v1?label=&message=POST&color=brightgreen&#41;)

[//]: # (![]&#40;https://img.shields.io/static/v1?label=&message=PUT&color=orange&#41;)

[//]: # (![]&#40;https://img.shields.io/static/v1?label=&message=DELETE&color=red&#41;)

[//]: # (![]&#40;https://img.shields.io/static/v1?label=&message=EMIT&color=brightgreen&#41;)

[//]: # (![]&#40;https://img.shields.io/static/v1?label=&message=ON&color=blue&#41;)

### 회원가입

> ![](https://img.shields.io/static/v1?label=&message=POST&color=brightgreen) <br>
> **/api/member**

<details markdown="1">
<summary>detail</summary>

#### Parameters

##### Body

| parameter | type |   description   | required |
|:---------:| :---: |:---------------:| :---: |
|    id     | string | 로그인 할 사용자의 아이디  | **Required** |
| password  | string | 로그인 할 사용자의 비밀번호 | **Required** |
|   name    | string |  로그인 할 사용자의 이름  | **Required** |
| phoneNum  | string | 로그인 할 사용자의 전화번호 | **Required** |

### Example request
```
{
  "id": "test123",
  "password": "$2a$10$DovfY8drJ2zBRi0rdwB7Ve3q3W4.FrYpIy34eY7nfyf5fahsSQrmS",
  "name": "Kim",
  "phoneNum": "010-1234-5678"
}
```

#### Example response

<summary>200 Ok : 성공적으로 로그인 된 경우</summary>

```
{
}
```

</details>
<br>

### 로그인

> ![](https://img.shields.io/static/v1?label=&message=POST&color=brightgreen) <br>
> **/api/login**

<details markdown="1">
<summary>detail</summary>

#### Parameters

##### Body

| parameter | type |   description   | required |
|:---------:| :---: |:---------------:| :---: |
|    id     | string | 로그인 할 사용자의 아이디  | **Required** |
| password  | string | 로그인 할 사용자의 비밀번호 | **Required** |

### Example request
```
{
  "id": "test123",
  "password": "$2a$10$DovfY8drJ2zBRi0rdwB7Ve3q3W4.FrYpIy34eY7nfyf5fahsSQrmS",
}
```

#### Example response

<summary>200 Ok : 성공적으로 로그인 된 경우</summary>

```
{
    "message", "Login successful"
}
```

<summary>401 : 로그인이 실패한 경우</summary>

```
{
    "message", "Login failed"
}
```

</details>
<br>

### 사용자 정보 확인

> ![](https://img.shields.io/static/v1?label=&message=GET&color=blue)<br>
> **/api/member/info**

<details markdown="1">
<summary>detail</summary>

#### Example response

<summary>200 Ok : 로그인 이후 JWT로 성공적으로 조회가 된 경우</summary>

```
{
  "id": "test123",
  "name": "Kim",
  "phoneNum": "010-1234-5678"
}
```

</details>
<br>

### 사용자 위치 저장

> ![](https://img.shields.io/static/v1?label=&message=POST&color=brightgreen) <br>
> **/geocoding/userlocation**

<details markdown="1">
<summary>detail</summary>

#### Parameters

##### Body

|  parameter   | type |     description     | required |
|:------------:| :---: |:-------------------:| :---: |
|      id      | string |   로그인 한 사용자의 아이디    | **Required** |
| locationName | string | 로그인 한 사용자의 위치 한글 주소 | **Required** |
|   latitude   | string |    로그인 한 사용자의 위도    | **Required** |
|  longitude   | string |    로그인 한 사용자의 경도    | **Required** |

#### Example request
```
{
  "id": "test123",
  "locationName": "대한민국 서울특별시 마포구 상수동 72-1",
  "latitude": "37.55253676097769",
  "longitude": "126.9249077331825"
}
```

<summary>200 Ok : 성공적으로 로그인 된 경우</summary>

```
{
}
```

</details>
<br>

### Naver Map URL Scheme

> ![](https://img.shields.io/static/v1?label=&message=GET&color=blue)<br>
> **/{userId}/{shelterName}**

<details markdown="1">
<summary>detail</summary>

#### Parameters

##### Body

|  parameter  | type |     description     | required |
|:-----------:| :---: |:-------------------:| :---: |
|   userId    | string |   로그인 한 사용자의 아이디    | **Required** |
| shelterName | string | 로그인 한 사용자의 위치 한글 주소 | **Required** |

#### Example response

<summary>200 Ok : 성공적으로 작동한 경우</summary>

```
{
    "url": "nmap://route/walk?slat=37.5526903&slng=37.5526903&sname=%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD+%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EB%A7%88%ED%8F%AC%EA%B5%AC+%EC%99%80%EC%9A%B0%EC%82%B0%EB%A1%9C+94&dlat=37.54707874&dlng=126.9536708&dname=%EB%A7%88%ED%8F%ACT%ED%83%80%EC%9A%B4%28%EC%A7%80%ED%95%982%7E%EC%A7%80%ED%95%985%EC%B8%B5%EC%A3%BC%EC%B0%A8%EC%9E%A5%29&appname=com.example.aiml_mobile_2024"
}
```

</details>
<br>

### 모든 포스트 조회

> ![](https://img.shields.io/static/v1?label=&message=GET&color=blue)<br>
> **/post/**

<details markdown="1">
<summary>detail</summary>

#### Example response

<summary>200 Ok : 성공적으로 모든 포스트가 조회된 경우</summary>

```
{
    {
        "num": "1",
        "id": "test1",
        "title": "title1",
        "content": "content1"
    },
    {
        "num": "2",
        "id": "test2",
        "title": "title2",
        "content": "content2"
    }
}
```

</details>
<br>

### 특정 포스트 조회

> ![](https://img.shields.io/static/v1?label=&message=GET&color=blue)<br>
> **/post/{num}**|

<details markdown="1">
<summary>detail</summary>

#### Parameters

##### Body

| parameter | type | description | required |
|:---------:| :---: |:-----------:| :---: |
|    num    | string |   포스트의 번호   | **Required** |

#### Example response

<summary>200 Ok : 성공적으로 특정 포스트가 조회된 경우</summary>

```
{
    "num": "1",
    "id": "test1",
    "title": "title1",
    "content": "content1"
}
```

</details>
<br>

### 포스트 업로드

> ![](https://img.shields.io/static/v1?label=&message=POST&color=brightgreen) <br>
> **/post/**

<details markdown="1">
<summary>detail</summary>

#### Parameters

##### Body

| parameter | type |  description   | required |
|:---------:| :---: |:--------------:| :---: |
|    num    | string |   포스트의 작성 번호   | **Required** |
|    id     | string | 로그인 한 사용자의 아이디 | **Required** |
|   title   | string |    포스트의 제목     | **Required** |
|  content  | string |    포스트의 내용     | **Required** |

#### Example request
```
{
    "num": "1",
    "id": "test1",
    "title": "title1",
    "content": "content1"
}
```

<summary>200 Ok : 성공적으로 로그인 된 경우</summary>

```
{
}
```

</details>
<br>

### 포스트 업데이트

> ![](https://img.shields.io/static/v1?label=&message=PUT&color=orange)<br/>
> **/post/{num}**

<details markdown="1">
<summary>detail</summary>

#### Parameters

##### Body

| parameter | type | description | required |
|:---------:| :---: |:-----------:| :---: |
|    num    | string |   포스트의 번호   | **Required** |

#### Example response

<summary>200 Ok : 성공적으로 포스트가 업데이트 된 경우</summary>

```
{
}
```

</details>
<br>

### 포스트 삭제

>![](https://img.shields.io/static/v1?label=&message=DELETE&color=red)<br/>
> **/post/{num}**

<details markdown="1">
<summary>detail</summary>

#### Parameters

##### Body

| parameter | type | description | required |
|:---------:| :---: |:-----------:| :---: |
|    num    | string |   포스트의 번호   | **Required** |

#### Example response

<summary>200 Ok : 성공적으로 포스트가 삭제된 경우</summary>

```
{
}
```

</details>
<br>