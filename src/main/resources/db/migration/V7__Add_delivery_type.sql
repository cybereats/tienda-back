-- Add delivery_type column to user_order table
ALTER TABLE user_order ADD COLUMN delivery_type VARCHAR(20) DEFAULT 'TABLE';

-- Update existing records
UPDATE user_order SET delivery_type = 'TABLE' WHERE delivery_type IS NULL;
