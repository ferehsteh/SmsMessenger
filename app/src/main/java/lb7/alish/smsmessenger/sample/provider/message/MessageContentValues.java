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

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import lb7.alish.smsmessenger.sample.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code message} table.
 */
@SuppressWarnings({"ConstantConditions", "unused"})
public class MessageContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MessageColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MessageSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The context to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MessageSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * number of contact
     */
    public MessageContentValues putNumber(@Nullable String value) {
        mContentValues.put(MessageColumns.NUMBER, value);
        return this;
    }

    public MessageContentValues putNumberNull() {
        mContentValues.putNull(MessageColumns.NUMBER);
        return this;
    }

    /**
     * body of Message
     */
    public MessageContentValues putMessageText(@Nullable String value) {
        mContentValues.put(MessageColumns.MESSAGE_TEXT, value);
        return this;
    }

    public MessageContentValues putMessageTextNull() {
        mContentValues.putNull(MessageColumns.MESSAGE_TEXT);
        return this;
    }

    public MessageContentValues putDate(@Nullable String value) {
        mContentValues.put(MessageColumns.DATE, value);
        return this;
    }

    public MessageContentValues putDateNull() {
        mContentValues.putNull(MessageColumns.DATE);
        return this;
    }

    public MessageContentValues putThreadId(@Nullable String value) {
        mContentValues.put(MessageColumns.THREAD_ID, value);
        return this;
    }

    public MessageContentValues putThreadIdNull() {
        mContentValues.putNull(MessageColumns.THREAD_ID);
        return this;
    }

    public MessageContentValues putSmsId(@Nullable String value) {
        mContentValues.put(MessageColumns.SMS_ID, value);
        return this;
    }

    public MessageContentValues putSmsIdNull() {
        mContentValues.putNull(MessageColumns.SMS_ID);
        return this;
    }

    public MessageContentValues putType(@Nullable DirectionType value) {
        mContentValues.put(MessageColumns.TYPE, value == null ? null : value.ordinal());
        return this;
    }

    public MessageContentValues putTypeNull() {
        mContentValues.putNull(MessageColumns.TYPE);
        return this;
    }

    public MessageContentValues putStatus(@Nullable String value) {
        mContentValues.put(MessageColumns.STATUS, value);
        return this;
    }

    public MessageContentValues putStatusNull() {
        mContentValues.putNull(MessageColumns.STATUS);
        return this;
    }
}
