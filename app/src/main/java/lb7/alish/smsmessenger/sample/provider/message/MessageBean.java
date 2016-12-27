/*
 * This source is part of the
 *      _____  ___   ____
 *  __ / / _ \/ _ | / __/___  _______ _
 * / // / , _/ __ |/ _/_/ _ \/ __/ _ `/
 * \___/_/|_/_/ |_/_/ (_)___/_/  \_, /
 *                              /___/
 * repository.
 *
 * Copyright (C) 2012-2015 Benoit 'BoD' Lubek (BoD@JRAF.org)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package lb7.alish.smsmessenger.sample.provider.message;

// @formatter:off

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Bean for the {@code message} table.
 */
@SuppressWarnings({"WeakerAccess", "unused", "ConstantConditions"})
public class MessageBean implements MessageModel {
    private long mId;
    private String mNumber;
    private String mMessageText;
    private Long mDate;
    private String mThreadId;
    private String mSmsId;
    private DirectionType mType;
    private String mStatus;

    /**
     * Instantiate a new MessageBean with specified values.
     */
    @NonNull
    public static MessageBean newInstance(long id, @Nullable String number, @Nullable String messageText, @Nullable Long date, @Nullable String threadId, @Nullable String smsId, @Nullable DirectionType type, @Nullable String status) {
        MessageBean res = new MessageBean();
        res.mId = id;
        res.mNumber = number;
        res.mMessageText = messageText;
        res.mDate = date;
        res.mThreadId = threadId;
        res.mSmsId = smsId;
        res.mType = type;
        res.mStatus = status;
        return res;
    }

    /**
     * Instantiate a new MessageBean with all the values copied from the given model.
     */
    @NonNull
    public static MessageBean copy(@NonNull MessageModel from) {
        MessageBean res = new MessageBean();
        res.mId = from.getId();
        res.mNumber = from.getNumber();
        res.mMessageText = from.getMessageText();
        res.mDate = from.getDate();
        res.mThreadId = from.getThreadId();
        res.mSmsId = from.getSmsId();
        res.mType = from.getType();
        res.mStatus = from.getStatus();
        return res;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Primary key.
     */
    @Override
    public long getId() {
        return mId;
    }

    /**
     * Primary key.
     */
    public void setId(long id) {
        mId = id;
    }

    /**
     * number of contact
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getNumber() {
        return mNumber;
    }

    /**
     * number of contact
     * Can be {@code null}.
     */
    public void setNumber(@Nullable String number) {
        mNumber = number;
    }

    /**
     * body of Message
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getMessageText() {
        return mMessageText;
    }

    /**
     * body of Message
     * Can be {@code null}.
     */
    public void setMessageText(@Nullable String messageText) {
        mMessageText = messageText;
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public Long getDate() {
        return mDate;
    }

    /**
     * Set the {@code date} value.
     * Can be {@code null}.
     */
    public void setDate(@Nullable Long date) {
        mDate = date;
    }

    /**
     * Get the {@code thread_id} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getThreadId() {
        return mThreadId;
    }

    /**
     * Set the {@code thread_id} value.
     * Can be {@code null}.
     */
    public void setThreadId(@Nullable String threadId) {
        mThreadId = threadId;
    }

    /**
     * Get the {@code sms_id} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getSmsId() {
        return mSmsId;
    }

    /**
     * Set the {@code sms_id} value.
     * Can be {@code null}.
     */
    public void setSmsId(@Nullable String smsId) {
        mSmsId = smsId;
    }

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public DirectionType getType() {
        return mType;
    }

    /**
     * Set the {@code type} value.
     * Can be {@code null}.
     */
    public void setType(@Nullable DirectionType type) {
        mType = type;
    }

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getStatus() {
        return mStatus;
    }

    /**
     * Set the {@code status} value.
     * Can be {@code null}.
     */
    public void setStatus(@Nullable String status) {
        mStatus = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageBean bean = (MessageBean) o;
        return mId == bean.mId;
    }

    @Override
    public int hashCode() {
        return (int) (mId ^ (mId >>> 32));
    }

    public static class Builder {
        private MessageBean mRes = new MessageBean();

        /**
         * Primary key.
         */
        public Builder id(long id) {
            mRes.mId = id;
            return this;
        }

        /**
         * number of contact
         * Can be {@code null}.
         */
        public Builder number(@Nullable String number) {
            mRes.mNumber = number;
            return this;
        }

        /**
         * body of Message
         * Can be {@code null}.
         */
        public Builder messageText(@Nullable String messageText) {
            mRes.mMessageText = messageText;
            return this;
        }

        /**
         * Set the {@code date} value.
         * Can be {@code null}.
         */
        public Builder date(@Nullable Long date) {
            mRes.mDate = date;
            return this;
        }

        /**
         * Set the {@code thread_id} value.
         * Can be {@code null}.
         */
        public Builder threadId(@Nullable String threadId) {
            mRes.mThreadId = threadId;
            return this;
        }

        /**
         * Set the {@code sms_id} value.
         * Can be {@code null}.
         */
        public Builder smsId(@Nullable String smsId) {
            mRes.mSmsId = smsId;
            return this;
        }

        /**
         * Set the {@code type} value.
         * Can be {@code null}.
         */
        public Builder type(@Nullable DirectionType type) {
            mRes.mType = type;
            return this;
        }

        /**
         * Set the {@code status} value.
         * Can be {@code null}.
         */
        public Builder status(@Nullable String status) {
            mRes.mStatus = status;
            return this;
        }

        /**
         * Get a new MessageBean built with the given values.
         */
        public MessageBean build() {
            return mRes;
        }
    }
}
