ALTER TABLE product
    MODIFY discount DOUBLE NULL;

ALTER TABLE category
    ADD parent_category VARCHAR(255) NULL;