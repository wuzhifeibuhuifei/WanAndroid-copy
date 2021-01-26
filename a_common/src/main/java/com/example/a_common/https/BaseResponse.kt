package com.example.a_common.https


/**
 * http的公共返回类型
 *
 */
class BaseResponse<T>(var data: T, var errorCode: Int = -1, var errorMsg: String = "") {


}