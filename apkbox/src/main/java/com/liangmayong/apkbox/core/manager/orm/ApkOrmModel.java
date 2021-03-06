/**
 The MIT License (MIT)

 Copyright (c) 2017 LiangMaYong ( ibeam@qq.com )

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/ or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 **/
package com.liangmayong.apkbox.core.manager.orm;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LiangMaYong on 2017/4/12.
 */
public class ApkOrmModel implements Parcelable {

    public ApkOrmModel() {
    }

    private long id = 0;
    private String apkPath;
    private String apkPackageName = "";
    private int apkVersionCode = 0;
    private String apkVersionName = "";
    private String apkDescription = "";
    private String apkSignture = "";
    private String apkSha1 = "";
    private int apkStatus = 1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApkPath() {
        return apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }

    public String getApkPackageName() {
        return apkPackageName;
    }

    public void setApkPackageName(String apkPackageName) {
        this.apkPackageName = apkPackageName;
    }

    public int getApkVersionCode() {
        return apkVersionCode;
    }

    public void setApkVersionCode(int apkVersionCode) {
        this.apkVersionCode = apkVersionCode;
    }

    public String getApkVersionName() {
        return apkVersionName;
    }

    public void setApkVersionName(String apkVersionName) {
        this.apkVersionName = apkVersionName;
    }

    public String getApkDescription() {
        return apkDescription;
    }

    public void setApkDescription(String apkDescription) {
        this.apkDescription = apkDescription;
    }

    public String getApkSignture() {
        return apkSignture;
    }

    public void setApkSignture(String apkSignture) {
        this.apkSignture = apkSignture;
    }

    public String getApkSha1() {
        return apkSha1;
    }

    public void setApkSha1(String apkSha1) {
        this.apkSha1 = apkSha1;
    }

    public int getApkStatus() {
        return apkStatus;
    }

    public void setApkStatus(int apkStatus) {
        this.apkStatus = apkStatus;
    }

    @Override
    public String toString() {
        return "ApkOrmModel{" +
                "id=" + id +
                ", apkPath='" + apkPath + '\'' +
                ", apkPackageName='" + apkPackageName + '\'' +
                ", apkVersionCode=" + apkVersionCode +
                ", apkVersionName='" + apkVersionName + '\'' +
                ", apkDescription='" + apkDescription + '\'' +
                ", apkSignture='" + apkSignture + '\'' +
                ", apkSha1='" + apkSha1 + '\'' +
                ", apkStatus=" + apkStatus +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.apkPath);
        dest.writeString(this.apkPackageName);
        dest.writeInt(this.apkVersionCode);
        dest.writeString(this.apkVersionName);
        dest.writeString(this.apkDescription);
        dest.writeString(this.apkSignture);
        dest.writeString(this.apkSha1);
        dest.writeInt(this.apkStatus);
    }

    protected ApkOrmModel(Parcel in) {
        this.id = in.readLong();
        this.apkPath = in.readString();
        this.apkPackageName = in.readString();
        this.apkVersionCode = in.readInt();
        this.apkVersionName = in.readString();
        this.apkDescription = in.readString();
        this.apkSignture = in.readString();
        this.apkSha1 = in.readString();
        this.apkStatus = in.readInt();
    }

    public static final Creator<ApkOrmModel> CREATOR = new Creator<ApkOrmModel>() {
        @Override
        public ApkOrmModel createFromParcel(Parcel source) {
            return new ApkOrmModel(source);
        }

        @Override
        public ApkOrmModel[] newArray(int size) {
            return new ApkOrmModel[size];
        }
    };
}
