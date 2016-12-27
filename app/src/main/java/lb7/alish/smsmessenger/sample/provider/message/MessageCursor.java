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

import android.database.Cursor;
import android.support.annotation.Nullable;

import lb7.alish.smsmessenger.sample.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code message} table.
 */
@SuppressWarnings({"WeakerAccess", "unused", "UnnecessaryLocalVariable"})
public class MessageCursor extends AbstractCursor implements MessageModel {
    public MessageCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    @Override
    public long getId() {
        Long res = getLongOrNull(MessageColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * number of contact
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getNumber() {
        String res = getStringOrNull(MessageColumns.NUMBER);
        return res;
    }

    /**
     * body of Message
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getMessageText() {
        String res = getStringOrNull(MessageColumns.MESSAGE_TEXT);
        return res;
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public Long getDate() {
        Long res = getLongOrNull(MessageColumns.DATE);
        return res;
    }

    /**
     * Get the {@code thread_id} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getThreadId() {
        String res = getStringOrNull(MessageColumns.THREAD_ID);
        return res;
    }

    /**
     * Get the {@code sms_id} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getSmsId() {
        String res = getStringOrNull(MessageColumns.SMS_ID);
        return res;
    }

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public DirectionType getType() {
        Integer intValue = getIntegerOrNull(MessageColumns.TYPE);
        if (intValue == null) return null;
        return DirectionType.values()[intValue];
    }

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getStatus() {
        String res = getStringOrNull(MessageColumns.STATUS);
        return res;
    }
}
